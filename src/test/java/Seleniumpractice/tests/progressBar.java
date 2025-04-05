package Seleniumpractice.tests;

import Seleniumpractice.DriverSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class progressBar{
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;


    @FindBy(id = "startStopButton")
    WebElement startbutton;

    @FindBy(xpath = "//div[@role='progressbar']")
    WebElement progressbarelem;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demoqa.com/progress-bar");
        PageFactory.initElements(driver,this);
        js = (JavascriptExecutor) driver;
    }

    @Test(priority = 1)
    public void verifyprogressBar(){

        js.executeScript("arguments[0].scrollIntoView();",startbutton);
        startbutton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(progressbarelem,"100%"));
        String progressBarText = progressbarelem.getText();
        System.out.println("Progress :"+progressBarText);
        Assert.assertEquals(progressBarText,"100%","Progress bar did not reach 100%");
    }

}