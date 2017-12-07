package com.sabre;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Andrzej on 2017-11-16.
 */


public class TestBase {
    static WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void waitForElementVisible(){

    }

    public void tearDown(){

    }

}
