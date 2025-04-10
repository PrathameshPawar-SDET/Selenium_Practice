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

public class droppable {
    WebDriver driver;
    Actions actions;
    JavascriptExecutor js;

    //simple tab

    @FindBy(id = "droppableExample-tab-simple")
    WebElement simpleTab;

    @FindBy(id = "draggable")
    WebElement simpleDrag;

    @FindBy(id = "droppable")
    WebElement simpledrop;


    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);
        driver.get("https://demoqa.com/droppable");
    }

    @Test(priority = 1)
    public void testSimpleDrop(){
        js.executeScript("arguments[0].scrollIntoView();",simpleTab);
        simpleTab.click();
        actions.clickAndHold(simpleDrag).moveToElement(simpledrop).release().perform();
        String simpletext = simpledrop.getText();
        Assert.assertEquals(simpletext,"Dropped!","Simple drop failed");
        System.out.println("Simple drag and drop succesfull");
    }
}