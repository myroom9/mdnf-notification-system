package com.mdnf.mdnf_notification_system.service;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public class CalendarTest {
    @Test
    void CalendarTest() {
        LocalDateTime now = LocalDateTime.now();
        Month month = now.getMonth();
        System.out.println("month = " + month.getValue());

        LocalDateTime target = now.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); //이번 달의 첫 월요일
        System.out.println(target);

        target = target.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); //돌아오는 월요일
        System.out.println(target);

        target = target.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); //돌아오는 월요일
        System.out.println(target);

        target = target.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); //돌아오는 월요일
        System.out.println(target);

        target = target.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); //돌아오는 월요일
        System.out.println(target);
    }
}
