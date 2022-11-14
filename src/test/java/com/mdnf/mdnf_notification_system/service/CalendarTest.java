package com.mdnf.mdnf_notification_system.service;

import com.mdnf.mdnf_notification_system.domain.Alarm;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarTest {
    @Test
    void CalendarTest() {
        LocalDateTime now = LocalDateTime.now();
        Month month = now.getMonth();
        System.out.println("month = " + month.getValue());

        // LocalDateTime target = now.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); //이번 달의 첫 월요일
        // System.out.println(target);
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek);
        LocalDateTime target = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); //돌아오는 월요일
        System.out.println(target);

        target = target.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); //돌아오는 월요일
        System.out.println(target);

        target = target.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); //돌아오는 월요일
        System.out.println(target);

        LocalDateTime test = now.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("test = " + test);
        if (test.isAfter(target.with(TemporalAdjusters.next(DayOfWeek.MONDAY)))) {
            target = target.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); //돌아오는 월요일
            System.out.println(target);
        }
    }

    @Test
    void dayOfWeekTest() {
        DayOfWeek tuesday = DayOfWeek.of(2);
        DayOfWeek wednesday = DayOfWeek.of(3);

        System.out.println(tuesday.compareTo(wednesday));
        System.out.println(wednesday.compareTo(tuesday));
    }

    @Test
    void eachDayScheduleTest() {
        LocalDateTime requestScheduleTime = LocalDateTime.of(2022,11,14,19,0,0);
        LocalDateTime now = LocalDateTime.now();
        List<DayOfWeek> days = Arrays.stream(new int[]{1,2,3,4})
                .mapToObj(DayOfWeek::of)
                .collect(Collectors.toList());


        LocalDateTime firstOfNextMonth = requestScheduleTime.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("firstOfNextMonth = " + firstOfNextMonth);
        ArrayList<Alarm> alarms = new ArrayList<>();

        days.forEach(o -> {
            LocalDateTime eachDaysScheduleTime = requestScheduleTime;
            DayOfWeek nowDay = now.getDayOfWeek();
            // 조건에 부합하면, nextWeek부터 진행
            if (o.compareTo(nowDay) >  0) {
                eachDaysScheduleTime = eachDaysScheduleTime.with(TemporalAdjusters.next(o));
                System.out.println("eachDaysScheduleTime = " + eachDaysScheduleTime);
            }

            if (eachDaysScheduleTime.isBefore(now)) {
                eachDaysScheduleTime = eachDaysScheduleTime.with(TemporalAdjusters.next(o));
            }

            while(eachDaysScheduleTime.isBefore(firstOfNextMonth)) {
                // alarmSendTime
                alarms.add(
                        Alarm.builder()
                                .title("test")
                                .content("testContent")
                                .alarmSchedule(eachDaysScheduleTime).build()
                );
                eachDaysScheduleTime = eachDaysScheduleTime.with(TemporalAdjusters.next(o));
            }
        });
        System.out.println("alarms = " + alarms);
    }
}
