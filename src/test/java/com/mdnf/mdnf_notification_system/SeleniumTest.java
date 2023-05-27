package com.mdnf.mdnf_notification_system;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumTest {
    @Test
    void getTitleTest() {
        System.setProperty("webdriver.chrome.driver", "/Users/wonhwiahn/dev_tool/chromedriver_mac64/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");

        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.get("https://www.bnkrmall.co.kr/goods/detail.do?gno=15029");

        new WebDriverWait(chromeDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.info_area")));

        WebElement element = chromeDriver.findElement(By.cssSelector("#wrap .detail_wrap .detail_right .info_area .type > .con .title"));
        String title = element.getText();
        System.out.println("title = " + title);
    }
}
