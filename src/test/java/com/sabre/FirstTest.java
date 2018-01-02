package com.sabre;

import org.testng.annotations.Test;

/**
 * Created by Andrzej on 2017-11-16.
 */
public class FirstTest extends TestBase {

    @Test
    public void shouldOpenBrowserTest(){
        driver.get("http://google.pl");
    }

}
