package com.mdnf.mdnf_notification_system.service;

import com.mdnf.mdnf_notification_system.domain.MdnfNotice;
import com.mdnf.mdnf_notification_system.feign.MdnfFeignClient;
import com.mdnf.mdnf_notification_system.feign.dto.MdnfResponse;
import com.mdnf.mdnf_notification_system.repository.MdnfNoticeRepository;
import com.mdnf.mdnf_notification_system.type.BoardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class MdnfContentService {
    private final MdnfFeignClient mdnfFeignClient;
    private final MdnfNoticeRepository mdnfNoticeRepository;

    /**
     * 던파모바일 공지사항 데이터 가져오기
     */
    public MdnfResponse.Notice getNoticeContents() {
        return mdnfFeignClient.getNoticeContents(1, 10);
    }

    /**
     * 던파모바일 공지사항 데이터 가져오기
     */
    public MdnfResponse.Notice getDevNoteContents() {
        return mdnfFeignClient.getDevNoteContents(1, 10);
    }

    /**
     * 던파모바일 공지사항 데이터 가져오기
     */
    public MdnfResponse.Notice getUpdateContents() {
        return mdnfFeignClient.getUpdateContents(1, 10);
    }

    /**
     * 던파모바일 공지사항
     */
    @Transactional
    public List<MdnfNotice> checkNewContentAndRenewContent(List<MdnfNotice> mdnfNotices) {
        BoardType boardType = mdnfNotices.get(0).getBoardType();
        MdnfNotice latestContent = mdnfNoticeRepository.findLatestContentByBoardType(boardType);

        // 최신글이 없다면, 가장 첫번째 글만 리턴
        if (ObjectUtils.isEmpty(latestContent)) {
            mdnfNotices.get(0).makeLatestContent();
            mdnfNotices.get(0).settingBoardType(boardType.getBoardType());
            mdnfNoticeRepository.save(mdnfNotices.get(0));
            return Collections.singletonList(mdnfNotices.get(0));
        }

        ArrayList<MdnfNotice> newContents = new ArrayList<>();
        int latestContentThreadId = latestContent.getThreadId();
        AtomicInteger beforeLatestContentIndex = new AtomicInteger();

        // 현재 api 컨텐츠와 최신글의 threadId 비교 후 갱신된 게시판글 추출
        IntStream.range(0, mdnfNotices.size())
                .forEach(o -> {
                    if (mdnfNotices.get(o).getThreadId() == latestContentThreadId) {
                        beforeLatestContentIndex.set(o);
                    }
                });

        // index가 0이 아니면 최신글이 갱신됐다는 뜻임

        Pattern pattern = Pattern.compile("^.*(정기 점검).*");

        // 공지사항은 정기점검만 노티하기
        if (beforeLatestContentIndex.get() != 0) {
            IntStream.range(0, beforeLatestContentIndex.get())
                    .filter(o -> pattern.matcher(mdnfNotices.get(o).getTitle()).matches())
                    .forEach(o -> newContents.add(mdnfNotices.get(o)));
            newContents.get(0).makeLatestContent();
            latestContent.makeNotLatestContent();
        }


        // 정기 점검이 완료 됐을 경우에 제목만 변경됨
        // 제목이 다를 경우 정기 점검 완료로 판단
        if (beforeLatestContentIndex.get() == 0 && pattern.matcher(mdnfNotices.get(0).getTitle()).matches()) {
            // 가장 최근 컨텐츠였던 글이 정기점검 글인지 확인
            if (Boolean.FALSE.equals(latestContent.getTitle().equals(mdnfNotices.get(0).getTitle()))
                && pattern.matcher(latestContent.getTitle()).matches()) {
                newContents.add(mdnfNotices.get(0));
                newContents.get(0).makeLatestContent();
                latestContent.makeNotLatestContent();
            }
        }

        mdnfNoticeRepository.saveAll(newContents);

        return newContents;
    }
}
