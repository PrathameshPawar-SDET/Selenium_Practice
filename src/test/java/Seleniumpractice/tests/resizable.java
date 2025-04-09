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
import java.util.ArrayList;
import java.util.List;
public class resizable {
    WebDriver driver;
    Actions actions;
    JavascriptExecutor js;

    @FindBy(id = "resizableBoxWithRestriction")
    WebElement restrictedBox;

    @FindBy(css = "#resizableBoxWithRestriction span")
    WebElement restrictedBoxHandle;

    @FindBy(id = "resizable")
    WebElement resizableBox;

    @FindBy(css = "#resizable span")
    WebElement resizableBoxHandle;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        actions =  new Actions(driver);
        driver.get("https://demoqa.com/resizable");
        js = (JavascriptExecutor) driver;
    }

    @Test(priority = 1)
    public void resizeRestrictedBox(){
        js.executeScript("arguments[0].scrollIntoView();",restrictedBox);
        Dimension initialsize = restrictedBox.getSize();
        System.out.println(initialsize);

        actions.clickAndHold(restrictedBoxHandle).moveByOffset(50,100).release().perform();
        Dimension newSize = restrictedBox.getSize();
        System.out.println(newSize);

    }

    @Test(priority = 2)
    public void resizeResizableBox(){
        js.executeScript("arguments[0].scrollIntoView();",resizableBox);
        Dimension initialsize = resizableBox.getSize();
        System.out.println(initialsize);

        actions.clickAndHold(resizableBoxHandle).moveByOffset(100,60).release().perform();
        Dimension newSize = resizableBox.getSize();
        System.out.println(newSize);

    }


}