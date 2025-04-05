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
public class toolTips {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions actions;

    @FindBy(id = "toolTipButton")
    WebElement toolTipButton;

    @FindBy(id = "toolTipTextField")
    WebElement toolTipTextField;

    @FindBy(css = ".tooltip-inner")
    WebElement tooltip;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://demoqa.com/tool-tips");
        PageFactory.initElements(driver,this);
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    @Test(priority = 1)
    public void verifyTooltiponButton(){
        js.executeScript("arguments[0].scrollIntoView();",toolTipButton);
        actions.moveToElement(toolTipButton).perform();
        wait.until(ExpectedConditions.textToBePresentInElement(tooltip,"Button"));
        Assert.assertTrue(tooltip.isDisplayed(),"Tooltip on button is not displayed");
        System.out.println("Tooltip on Button : "+tooltip.getText());
    }

    @Test(priority = 2)
    public void verifyTooltiponText(){
        js.executeScript("arguments[0].scrollIntoView();",toolTipTextField);
        actions.moveToElement(toolTipTextField);
        wait.until(ExpectedConditions.textToBePresentInElement(tooltip,"text field"));
        Assert.assertTrue(tooltip.isDisplayed(),"Tooltip on TextField in not displayed");
        System.out.println("Tooltip on Textfield : "+tooltip.getText());

    }

}