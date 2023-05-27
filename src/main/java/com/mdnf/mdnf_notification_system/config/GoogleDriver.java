package com.mdnf.mdnf_notification_system.config;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration(proxyBeanMethods = false)
public class GoogleDriver {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Bean
    public ChromeOptions getChromeOptions() {

        if ("local".equals(activeProfile)) {
            System.setProperty("webdriver.chrome.driver", "/Users/wonhwiahn/dev_tool/chromedriver_mac64/chromedriver");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");
            return chromeOptions;
        }

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--remote-allow-origins=*");

        return chromeOptions;
    }
}
