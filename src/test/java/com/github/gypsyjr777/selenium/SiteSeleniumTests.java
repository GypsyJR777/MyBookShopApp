package com.github.gypsyjr777.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SiteSeleniumTests {
    private static RemoteWebDriver driver;

    @BeforeAll
    static void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\Skillbox\\FromBookShopApp\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    public void testAllPages() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.mainPage()
                .goToGenres()
                .pause()
                .goToGenre()
                .pause()
                .goToBookFromGenre()
                .pause()
                .goToNews()
                .pause()
                .goToPopular()
                .pause()
                .goToBookFromPopular()
                .pause()
                .goToAuthors()
                .pause()
                .goToAuthor()
                .pause()
                .goToAllBooksFromAuthor()
                .pause()
                .goToBookFromAuthor()
                .pause()
                .mainPage()
                .pause();

        assertTrue(driver.getPageSource().contains("BOOKSHOP"));


    }
}
