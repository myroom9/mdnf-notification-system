package com.mdnf.mdnf_notification_system.domain;

import com.mdnf.mdnf_notification_system.feign.dto.MdnfResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
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

    public void makeLatestContent() {
        this.isLatestContent = true;
    }

    public void makeNotLatestContent() {
        this.isLatestContent = false;
    }

    @Builder
    public MdnfNotice(int threadId, String title, BigInteger createDate, BigInteger modifyDate, BigInteger threadModifyDate) {
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
