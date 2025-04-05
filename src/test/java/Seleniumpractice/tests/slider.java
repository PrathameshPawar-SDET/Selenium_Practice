package Seleniumpractice.tests;

import Seleniumpractice.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class slider{

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://demoqa.com/slider");
    }

    @Test(priority = 1)
    public void moveSlider(){
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));

        String inititalValue = slider.getDomAttribute("value");
        System.out.println("Initial Slider Value :"+inititalValue);

        Actions act = new Actions(driver);
        act.clickAndHold(slider).moveByOffset(50,0).release().perform();

        String newvalue = slider.getDomAttribute("value");
        System.out.println("New value after slding :"+newvalue);

        Assert.assertNotEquals(inititalValue,newvalue,"Slider Value does not change");
    }
}