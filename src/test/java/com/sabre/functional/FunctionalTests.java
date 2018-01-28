package com.sabre.functional;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;


public class FunctionalTests extends TestBase{

    @Test
    public void shouldLogin(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get("http://localhost:8090/#!/");
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement login = driver.findElement(By.name("login"));
        email.sendKeys("noah.williams@travel-sabre.com");
        password.sendKeys("Noah");
        login.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("app-header")));
    }

    @Test
    public void shouldAddCompleteFlight(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        // login
        driver.get("http://localhost:8090/#!/");
        WebElement email1 = driver.findElement(By.name("email"));
        WebElement password1= driver.findElement(By.name("password"));
        WebElement login1 = driver.findElement(By.name("login"));
        email1.sendKeys("noah.williams@travel-sabre.com");
        password1.sendKeys("Noah");
        login1.click();

        // add flight
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("app-header")));
        WebElement addFlight = driver.findElement(By.id("addFlight"));
        addFlight.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-flight-div")));
        WebElement adc = driver.findElement(By.name("airport-departure-code"));
        WebElement arc= driver.findElement(By.name("airport-arrival-code"));
        WebElement ac = driver.findElement(By.name("airline-code"));
        WebElement initialMiles = driver.findElement(By.name("initialMiles"));
        WebElement fc = driver.findElement(By.name("flight-class"));
        WebElement dfd = driver.findElement(By.name("departure-flight-date"));
        WebElement rfd = driver.findElement(By.name("return-flight-date"));
        WebElement afb = driver.findElement(By.name("add-flight-btn"));
        adc.sendKeys("A");
        arc.sendKeys("B");
        ac.sendKeys("C");
        initialMiles.sendKeys("1000");
        fc.sendKeys("BUSINESS");
        driver.findElement(By.name("departure-flight-date")).click();
        dfd.sendKeys("1985");
        driver.findElement(By.name("departure-flight-date")).sendKeys(Keys.TAB);
        dfd.sendKeys("06");
        dfd.sendKeys("28");

        driver.findElement(By.name("return-flight-date")).click();
        rfd.sendKeys("1985");
        driver.findElement(By.name("return-flight-date")).sendKeys(Keys.TAB);
        rfd.sendKeys("06");
        rfd.sendKeys("28");
        afb.click();

        // check browser returns to profile page which means everything is successful
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("app-header")));
    }

    @Test
    public void shouldntAddCompleteFlight(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        // login
        driver.get("http://localhost:8090/#!/");
        WebElement email1 = driver.findElement(By.name("email"));
        WebElement password1= driver.findElement(By.name("password"));
        WebElement login1 = driver.findElement(By.name("login"));
        email1.sendKeys("noah.williams@travel-sabre.com");
        password1.sendKeys("Noah");
        login1.click();

        // add flight

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("app-header")));
        WebElement addFlight = driver.findElement(By.id("addFlight"));
        addFlight.click();
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-flight-div")));
        WebElement adc = driver.findElement(By.name("airport-departure-code"));
        WebElement arc= driver.findElement(By.name("airport-arrival-code"));
        WebElement ac = driver.findElement(By.name("airline-code"));
        WebElement initialMiles = driver.findElement(By.name("initialMiles"));
        WebElement fc = driver.findElement(By.name("flight-class"));
        WebElement dfd = driver.findElement(By.name("departure-flight-date"));
        WebElement rfd = driver.findElement(By.name("return-flight-date"));
        WebElement afb = driver.findElement(By.name("add-flight-btn"));
        adc.sendKeys("A");
        arc.sendKeys("B");
        ac.sendKeys("C");
        initialMiles.sendKeys("1000");
        fc.sendKeys("asdassa");
        driver.findElement(By.name("departure-flight-date")).click();
        dfd.sendKeys("1985");
        driver.findElement(By.name("departure-flight-date")).sendKeys(Keys.TAB);
        dfd.sendKeys("06");
        dfd.sendKeys("28");

        driver.findElement(By.name("return-flight-date")).click();
        rfd.sendKeys("1985");
        driver.findElement(By.name("return-flight-date")).sendKeys(Keys.TAB);
        rfd.sendKeys("06");
        rfd.sendKeys("28");
        afb.click();

        // check if browser stays on the page since data is incorrect
        assertEquals(driver.getCurrentUrl(), currentUrl);

    }

    @Test
    public void shouldAddInCompleteFlight(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        // login
        driver.get("http://localhost:8090/#!/");
        WebElement email1 = driver.findElement(By.name("email"));
        WebElement password1= driver.findElement(By.name("password"));
        WebElement login1 = driver.findElement(By.name("login"));
        email1.sendKeys("noah.williams@travel-sabre.com");
        password1.sendKeys("Noah");
        login1.click();

        // add flight
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("app-header")));
        WebElement addFlight = driver.findElement(By.id("addFlight"));
        addFlight.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-flight-div")));
        WebElement adc = driver.findElement(By.name("airport-departure-code"));
        WebElement arc= driver.findElement(By.name("airport-arrival-code"));
        WebElement ac = driver.findElement(By.name("airline-code"));
        WebElement fc = driver.findElement(By.name("flight-class"));
        WebElement rt = driver.findElement(By.name("return-ticket"));
        WebElement dfd = driver.findElement(By.name("departure-flight-date"));
        WebElement rfd = driver.findElement(By.name("return-flight-date"));
        WebElement afb = driver.findElement(By.name("add-flight-btn"));
        adc.sendKeys("ORD");
        arc.sendKeys("SLC");
        rt.click();
        ac.sendKeys("A");
        fc.sendKeys("BUSINESS");
        //setting date
        driver.findElement(By.name("departure-flight-date")).click();
        dfd.sendKeys("1985");
        driver.findElement(By.name("departure-flight-date")).sendKeys(Keys.TAB);
        dfd.sendKeys("06");
        dfd.sendKeys("28");

        driver.findElement(By.name("return-flight-date")).click();
        rfd.sendKeys("1985");
        driver.findElement(By.name("return-flight-date")).sendKeys(Keys.TAB);
        rfd.sendKeys("06");
        rfd.sendKeys("28");

        afb.click();

        // check browser returns to profile page which means everything is successful
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("app-header")));
    }

    @Test
    public void shouldAddUser(){
        // login
        driver.get("http://localhost:8090/#!/");
        String url = driver.getCurrentUrl();
        WebElement login1 = driver.findElement(By.name("login-as-admin"));
        login1.click();

        // add user
        WebElement addUser = driver.findElement(By.name("add-user"));
        addUser.click();

        // fill fields

        WebElement fn = driver.findElement(By.name("first-name"));
        WebElement ln= driver.findElement(By.name("last-name"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement initialMiles = driver.findElement(By.name("initialMiles"));
        WebElement date = driver.findElement(By.name("date"));
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement addUserButton = driver.findElement(By.name("add-user"));

        fn.sendKeys("A");
        ln.sendKeys("B");
        email.sendKeys("A@B");
        initialMiles.sendKeys("5000");
        date.sendKeys("2018-01-16");
        username.sendKeys("D");
        password.sendKeys("V");
        addUserButton.click();

        // assert browser properly returns to login page
        assertEquals( driver.getCurrentUrl(), url );
    }


}