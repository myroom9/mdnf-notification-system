package com.mdnf.mdnf_notification_system.service;

import com.mdnf.mdnf_notification_system.domain.MdnfNotice;
import com.mdnf.mdnf_notification_system.feign.MdnfFeignClient;
import com.mdnf.mdnf_notification_system.feign.dto.MdnfResponse;
import com.mdnf.mdnf_notification_system.repository.MdnfNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
     * 던파모바일 공지사항
     */
    @Transactional
    public List<MdnfNotice> checkNewNoticeAndRenewNotice(List<MdnfNotice> mdnfNotices) {
        MdnfNotice latestContent = mdnfNoticeRepository.findLatestContent();

        // 최신글이 없다면, 가장 첫번째 글만 리턴
        if (latestContent == null) {
            mdnfNotices.get(0).makeLatestContent();
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
        if (beforeLatestContentIndex.get() != 0) {
            IntStream.range(0, beforeLatestContentIndex.get() - 1)
                    .forEach(o -> newContents.add(mdnfNotices.get(o)));

            latestContent.makeNotLatestContent();
            newContents.get(0).makeLatestContent();

            mdnfNoticeRepository.saveAll(newContents);
        }

        return newContents;
    }
}
