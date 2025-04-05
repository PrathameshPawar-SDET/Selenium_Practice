package Seleniumpractice.tests;

import Seleniumpractice.DriverSingleton;
import org.openqa.selenium.*;
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
    public void moveSliderwithoffset(){
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));

        String inititalValue = slider.getDomAttribute("value");
        System.out.println("Initial Slider Value :"+inititalValue);

        Actions act = new Actions(driver);
        act.clickAndHold(slider).moveByOffset(50,0).release().perform();

        String newvalue = slider.getDomAttribute("value");
        System.out.println("New value after slding :"+newvalue);

        Assert.assertNotEquals(inititalValue,newvalue,"Slider Value does not change");
    }

    @Test(priority = 2)
    public void moveSliderwithevalue() throws InterruptedException{
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));

        int inititalValue = Integer.parseInt(slider.getDomAttribute("value"));
        System.out.println("Initial Slider Value :"+inititalValue);

        int targetvalue = 10;
        int diff = Math.abs(targetvalue-inititalValue);
        int click=0;

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].focus();", slider);

        Thread.sleep(200);
        if (targetvalue > inititalValue) {
            for (int i = 0; i < diff; i++) {
                slider.sendKeys(Keys.ARROW_RIGHT);
                click++;
                Thread.sleep(50);
            }
        } else {
            for (int i = 0; i < diff; i++) {
                slider.sendKeys(Keys.ARROW_LEFT);
                click++;
                Thread.sleep(50);
            }
        }
        System.out.println("Click count :"+click);
        Assert.assertEquals(diff,click,"Click count is not matching with diffrence value");
        String newvalue = slider.getDomAttribute("value");
        System.out.println("New value after slding :"+newvalue);

        Assert.assertNotEquals(inititalValue,newvalue,"Slider Value does not change");

    }

}