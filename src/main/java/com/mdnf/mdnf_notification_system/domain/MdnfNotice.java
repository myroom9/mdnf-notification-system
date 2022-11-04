package com.mdnf.mdnf_notification_system.domain;

import com.mdnf.mdnf_notification_system.feign.dto.MdnfResponse;
import com.mdnf.mdnf_notification_system.type.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter @ToString
@Entity
@DynamicUpdate
@Table(name = "mdnf_notice")
public class MdnfNotice {

    protected MdnfNotice() {}

    public void settingBoardType(String boardType) {
        this.boardType = boardType;
    }

    public void makeLatestContent() {
        this.isLatestContent = true;
    }

    public void makeNotLatestContent() {
        this.isLatestContent = false;
    }

    @Builder
    public MdnfNotice(String boardType, int threadId, String title, BigInteger createDate, BigInteger modifyDate, BigInteger threadModifyDate) {
        this.boardType = boardType;
        this.threadId = threadId;
        this.title = title;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.threadModifyDate = threadModifyDate;
    }


    public static List<MdnfNotice> mdnfNoticeMapper(MdnfResponse.Notice response) {
        ArrayList<MdnfNotice> mappedData = new ArrayList<>();
        response.getThreads().forEach(o -> {
            mappedData.add(
                    MdnfNotice.builder()
                            .boardType(BoardType.NOTICE.getBoardType())
                            .threadId(o.getThreadId())
                            .title(o.getTitle())
                            .createDate(o.getCreateDate())
                            .modifyDate(o.getModifyDate())
                            .threadModifyDate(o.getThreadModifyDate())
                            .build());
        });

        return mappedData;
    }

    public static List<MdnfNotice> mdnfDevNoteMapper(MdnfResponse.Notice response) {
        ArrayList<MdnfNotice> mappedData = new ArrayList<>();
        response.getThreads().forEach(o -> {
            mappedData.add(
                    MdnfNotice.builder()
                            .boardType(BoardType.DEV_NOTE.getBoardType())
                            .threadId(o.getThreadId())
                            .title(o.getTitle())
                            .createDate(o.getCreateDate())
                            .modifyDate(o.getModifyDate())
                            .threadModifyDate(o.getThreadModifyDate())
                            .build());
        });

        return mappedData;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "board_type", length = 20)
    private String boardType;

    @Column(name = "thread_id")
    private int threadId;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "create_date")
    private BigInteger createDate;

    @Column(name = "modify_date")
    private BigInteger modifyDate;

    @Column(name = "thread_modify_date")
    private BigInteger threadModifyDate;

    @Column(name = "is_latest_content")
    private boolean isLatestContent;
}
