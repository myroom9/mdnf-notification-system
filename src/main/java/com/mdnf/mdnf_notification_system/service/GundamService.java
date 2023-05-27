package com.mdnf.mdnf_notification_system.service;

import com.mdnf.mdnf_notification_system.domain.Gundam;
import com.mdnf.mdnf_notification_system.dto.gundam.request.RegisterGundamSite;
import com.mdnf.mdnf_notification_system.repository.GundamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GundamService {

    private final GundamRepository gundamRepository;

    /**
     * 건담 입고 사이트 등록
     */
    @Transactional
    public void registerGundamSite(RegisterGundamSite request) {

        System.setProperty("webdriver.chrome.driver", "/Users/wonhwiahn/dev_tool/chromedriver_mac64/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");

        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.get(request.getUrl());

        new WebDriverWait(chromeDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.info_area")));

        WebElement element = chromeDriver.findElement(By.cssSelector("#wrap .detail_wrap .detail_right .info_area .type > .con .title"));
        String title = element.getText();

        Gundam gundam = Gundam.builder()
                .url(request.getUrl())
                .nickname(request.getNickname())
                .title(title)
                .isSoldOut(true)
                .isSendAlarm(true)
                .soldOutDatetime(LocalDateTime.now())
                .build();
        log.info("건담 사이트 저장: {}", gundam);
        gundamRepository.save(gundam);
    }

    /**
     * 등록한 사이트중 건담 품절 사이트 확인하기
     */
    @Transactional
    public void checkSoldOutSite() {

        List<Gundam> gundamList = gundamRepository.findAll();

        System.setProperty("webdriver.chrome.driver", "/Users/wonhwiahn/dev_tool/chromedriver_mac64/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");

        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);

        // chromeDriver.get("https://www.bnkrmall.co.kr/goods/detail.do?gno=15029");
        // chromeDriver.get("https://www.bnkrmall.co.kr/goods/detail.do?gno=60053");

        for(Gundam o : gundamList) {
            try {

                chromeDriver.get(o.getUrl());

                new WebDriverWait(chromeDriver, Duration.ofSeconds(30))
                        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.info_area")));

                // 품절
                // WebElement element = chromeDriver.findElement(By.cssSelector("#wrap .detail_wrap .detail_right .info_area .type.basic.soldOut .show_soldOut"));
                // 구매 가능 상품
                WebElement element = chromeDriver.findElement(By.cssSelector("#wrap .detail_wrap .detail_right .info_area .type.basic.default .show_default"));
                String displayValue = element.getCssValue("display");

                // 구매 가능 상품 && 현재 상태가 품절 && 알람 발송을 진행한 상태
                if ("block".equals(displayValue) && o.isSoldOut() && o.isSendAlarm()) {
                    o.setNotSoldOutGoods();
                    gundamRepository.save(o);
                }

            } catch (Exception e) {
                log.info("품절 상품 발생");
                if (!o.isSoldOut() && o.isSendAlarm()) {
                    o.setSoldOutGoods();
                    gundamRepository.save(o);
                }
            }
        }

    }
}
