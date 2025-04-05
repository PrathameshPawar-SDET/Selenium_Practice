package Seleniumpractice.tests;

import Seleniumpractice.DriverSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
public class tabs {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;


    @FindBy(id = "demo-tab-what")
    WebElement whatTab;

    @FindBy(id = "demo-tabpane-what")
    WebElement whatContent;

    @FindBy(id = "demo-tab-origin")
    WebElement origintab;

    @FindBy(id = "demo-tabpane-origin")
    WebElement originContent;

    @FindBy(id = "demo-tab-use")
    WebElement useTab;

    @FindBy(id = "demo-tabpane-use")
    WebElement useContent;



    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://demoqa.com/tabs");
        PageFactory.initElements(driver,this);
        js = (JavascriptExecutor) driver;
    }

    @Test(priority = 1)
    public void verifywhattab(){
        js.executeScript("arguments[0].scrollIntoView();",whatTab);
        whatTab.click();
        Assert.assertTrue(whatContent.isDisplayed(),"What tab content is not displayed");
        System.out.println("What tab content : "+whatContent.getText().substring(0,50)+"...");

    }

    @Test(priority = 2)
    public void verifyorigintab(){
        js.executeScript("arguments[0].scrollIntoView();",origintab);
        origintab.click();
        Assert.assertTrue(originContent.isDisplayed(),"Origin tab content is not displayed");
        System.out.println("Origin tab content : "+originContent.getText().substring(0,50)+"...");

    }

    @Test(priority = 3)
    public void verifyusetab(){
        js.executeScript("arguments[0].scrollIntoView();",useTab);
        useTab.click();
        Assert.assertTrue(useContent.isDisplayed(),"Use tab content is not displayed");
        System.out.println("Use tab content : "+useContent.getText().substring(0,50)+"...");

    }

}