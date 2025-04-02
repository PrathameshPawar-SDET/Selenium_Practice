package Seleniumpractice.tests;

import Seleniumpractice.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

public class Baseclass {

    protected static WebDriver driver;


    @AfterSuite(alwaysRun = true)
    public void closedriver(){
        if (driver != null) {
            driver.quit();
        }
    }    @BeforeTest(alwaysRun = true)
    public void setupsuite(){
        driver = DriverSingleton.getDriver();

    }





}
