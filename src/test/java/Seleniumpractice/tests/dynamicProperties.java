package Seleniumpractice.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import Seleniumpractice.DriverSingleton;

import java.time.Duration;
import java.util.Set;
public class dynamicProperties {

    WebDriver driver = DriverSingleton.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


    @Test(priority = 1)
    public void navigate(){
        driver.navigate().to("https://demoqa.com/dynamic-properties");
    }

    @Test(priority = 2)
    public void testenableafter5second(){

        WebElement enableButton = driver.findElement(By.id("enableAfter"));

        wait.until(ExpectedConditions.elementToBeClickable(enableButton));

        Assert.assertTrue(enableButton.isEnabled(),"Button Does not become enabled");

        System.out.println("'Will enable 5 seconds' Button is enabled");
    }

    @Test(priority = 3)
    public void colorchange(){

        WebElement colorchangebutton = driver.findElement(By.id("colorChange"));

//        String initclass = colorchangebutton.getDomAttribute("class");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String initclass = (String) js.executeScript("return document.getElementById('colorChange').className;");
        System.out.println(initclass);

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(colorchangebutton,"class",initclass)));

        String newclass = colorchangebutton.getDomAttribute("class");
        Assert.assertNotEquals(initclass,newclass,"Color did not changed");
        System.out.println("Button color has been changed from " +initclass+ "to " +newclass);
    }

    @Test(priority = 4)
    public void testvisibleafter5seconds(){
        WebElement visiblebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter")));
        Assert.assertTrue(visiblebutton.isEnabled(),"Button is not visible");
        System.out.println("Button is visible after 5 seconds");


    }

}