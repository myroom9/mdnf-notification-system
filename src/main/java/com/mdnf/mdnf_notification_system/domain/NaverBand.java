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
@Table(name = "naver_band")
public class NaverBand {

    protected NaverBand() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "access_token", length = 1000)
    private String accessToken;

    @Column(name = "band_key")
    private String bandKey;

}

