package Seleniumpractice.tests;

import Seleniumpractice.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

public class Baseclass {

    protected static WebDriver driver;

    public void setupsuite(){
        driver = DriverSingleton.getDriver();

    }

    @AfterSuite(alwaysRun = true)
    public void closedriver() {
        if (driver != null) {
            driver.quit();
        }
    }


}
