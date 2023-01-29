package com.github.gypsyjr777.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MainPage {
    private String url = "http://localhost:8085/";
    private RemoteWebDriver driver;
    public MainPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public MainPage mainPage() {
        driver.get(url);
        return this;
    }

    public MainPage pause() throws InterruptedException {
        Thread.sleep(2000);
        return this;
    }

    public MainPage setUpSearchToken(String token) {
        WebElement element = driver.findElement(By.id("query"));
        element.sendKeys(token);
        return this;
    }

    public MainPage submitSearch() {
        WebElement element = driver.findElement(By.id("search"));
        element.submit();
        return this;
    }

    public MainPage goToGenres() {
        WebElement element = driver.findElement(By.id("genres"));
        element.click();
        return this;
    }

    public MainPage goToGenre() {
        WebElement element = driver.findElement(By.linkText("Лёгкое чтение (28)"));
        element.click();
        return this;
    }

    public MainPage goToBookFromGenre() {
        WebElement element = driver.findElement(By.xpath("/html/body/div/div/main/div/div/div[1]/div[1]/a/img"));
        element.click();
        return this;
    }

    public MainPage goToNews() {
        WebElement element = driver.findElement(By.id("recent"));
        element.click();
        return this;
    }

    public MainPage goToPopular() {
        WebElement element = driver.findElement(By.id("popular"));
        element.click();
        return this;
    }

    public MainPage goToBookFromPopular() {
        WebElement element = driver.findElement(By.xpath("/html/body/div/div/main/div/div[2]/div[1]/div[1]/a/img"));
        element.click();
        return this;
    }

    public MainPage goToAuthors() {
        WebElement element = driver.findElement(By.id("authors"));
        element.click();
        return this;
    }

    public MainPage goToAuthor() {
        WebElement element = driver.findElement(By.linkText("Alsford Aleda"));
        element.click();
        return this;
    }

    public MainPage goToAllBooksFromAuthor() {
        WebElement element = driver.findElement(By.linkText("All books by the author(48)"));
        element.click();
        return this;
    }

    public MainPage goToBookFromAuthor() {
        WebElement element = driver.findElement(By.xpath("/html/body/div/div/main/div/div/div[1]/div[1]/a/img"));
        element.click();
        return this;
    }
}
