package Seleniumpractice.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Seleniumpractice.DriverSingleton;

import java.time.Duration;
import java.util.List;
import java.util.Set;
public class nestedFrames {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.navigate().to("https://demoqa.com/nestedframes");
    }

    @Test(priority = 1)
    public void handlenestedframes(){
        driver.switchTo().frame("frame1");
        WebElement parentframetext = driver.findElement(By.tagName("body"));
        System.out.println("Parent Frame Text :"+parentframetext.getText());
        Assert.assertTrue(parentframetext.getText().contains("Parent frame"),"Parent Frame Text is Mismatched");
        WebElement childframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(childframe);
        WebElement childframetext = driver.findElement(By.tagName("p"));
        System.out.println("Child Frame Text :"+childframetext.getText());
        Assert.assertEquals(childframetext.getText(),"Child Iframe","ChildFrame text is Mismatched");
        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
    }
}