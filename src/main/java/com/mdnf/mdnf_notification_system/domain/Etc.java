package com.mdnf.mdnf_notification_system.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "etcs")
public class Etc {

    protected Etc() {}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", length = 255)
    private String type;

    @Column(name = "extra1", length = 1000)
    private String extra1;

    @Column(name = "extra2", length = 1000)
    private String extra2;

    @Column(name = "extra3", length = 1000)
    private String extra3;
}
