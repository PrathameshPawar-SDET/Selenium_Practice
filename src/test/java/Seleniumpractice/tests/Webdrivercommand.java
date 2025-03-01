package Seleniumpractice.tests;

import org.openqa.selenium.WebDriver;
import Seleniumpractice.DriverSingleton;
import org.testng.annotations.Test;

public class Webdrivercommand {
    WebDriver driver = DriverSingleton.getDriver();

    @Test
    public void WebDriverCommands(){
        driver.get("https://demoqa.com/");

    }

}