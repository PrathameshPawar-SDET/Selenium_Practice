package Seleniumpractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSingleton {
    private static WebDriver driver = null;
    private DriverSingleton(){}

    public static WebDriver getDriver(){
        if(driver==null){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            System.out.println("createDriver()");
        }
        driver.manage().window().maximize();
        return driver;
    }

}
