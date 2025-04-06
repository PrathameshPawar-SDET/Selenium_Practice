package Seleniumpractice.tests;

import Seleniumpractice.DriverSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
public class menu {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js;

    @FindBy(xpath = "//a[text()='Main Item 2']")
    WebElement mainItem2;

    @FindBy(xpath = "//a[text()='SUB SUB LIST »']")
    WebElement SubList;

    @FindBy(xpath = "//a[text()='Sub Sub Item 1']")
    WebElement subSubList;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://demoqa.com/menu");
        PageFactory.initElements(driver,this);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

    }

    @Test(priority = 1)
    public void verifySubSubItemisvisible(){
        js.executeScript("arguments[0].scrollIntoView();",mainItem2);
        actions.moveToElement(mainItem2).perform();
        System.out.println("Visited Main item 2");
        wait.until(ExpectedConditions.visibilityOf(SubList));
        actions.moveToElement(SubList).perform();
        System.out.println("Visited Sub List menu");
        wait.until(ExpectedConditions.visibilityOf(subSubList));
        actions.moveToElement(subSubList).perform();
        System.out.println("Visited Sub-sub List menu");


        Assert.assertTrue(subSubList.isDisplayed(),"SubSub Menu Item is not displayed");
        Assert.assertEquals(subSubList.getText(),"Sub Sub Item 1","Sub-Sub Item text is mismtached!");
    }

}