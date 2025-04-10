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
    WebDriverWait wait;

    //simple tab

    @FindBy(id = "droppableExample-tab-simple")
    WebElement simpleTab;

    @FindBy(id = "draggable")
    WebElement simpleDrag;

    @FindBy(id = "droppable")
    WebElement simpledrop;

    //Accept tab

    @FindBy(id = "droppableExample-tab-accept")
    WebElement acceptTab;

    @FindBy(id = "acceptable")
    WebElement acceptableDrag;

    @FindBy(id = "notAcceptable")
    WebElement notAcceptableDrag;

    @FindBy(css = "#acceptDropContainer #droppable")
    WebElement acceptdrop;


    //Prevent propogation

    @FindBy(id = "droppableExample-tab-preventPropogation")
    WebElement preventTab;

    @FindBy(id = "dragBox")
    WebElement propogationdrag;

    @FindBy(id = "notGreedyDropBox")
    WebElement notGreedyOuter;

    @FindBy(id = "notGreedyInnerDropBox")
    WebElement notGreedyInner;

    @FindBy(id = "greedyDropBox")
    WebElement greedyOuter;

    @FindBy(id = "greedyDropBoxInner")
    WebElement greedyIner;


    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);
        driver.get("https://demoqa.com/droppable");
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    }

    @Test(priority = 1)
    public void testSimpleDrop(){
        js.executeScript("arguments[0].scrollIntoView();",simpleTab);
        simpleTab.click();
        actions.dragAndDrop(simpleDrag,simpledrop).perform();
        String simpletext = simpledrop.getText();
        Assert.assertEquals(simpletext,"Dropped!","Simple drop failed");
        System.out.println("Simple drag and drop succesfull");
    }

    @Test(priority = 2)
    public void testAcceptDrop(){
        js.executeScript("arguments[0].scrollIntoView();",acceptTab);
        acceptTab.click();
        wait.until(ExpectedConditions.visibilityOf(acceptdrop));

        actions.dragAndDrop(notAcceptableDrag,acceptdrop).perform();
        Assert.assertNotEquals(acceptdrop.getText(),"Dropped!","Not acceptable element should not be accepted");
        System.out.println("Not acceptable tab is not acceptable when drop");

        actions.dragAndDrop(acceptableDrag,acceptdrop).perform();
        Assert.assertEquals(acceptdrop.getText(),"Dropped!","Acceptable element was not acceptable");
        System.out.println("Acceptable element is accepted when drop");
    }

    @Test(priority = 3)
    public void testPreventPropogation(){
        js.executeScript("arguments[0].scrollIntoView();",preventTab);
        preventTab.click();
        actions.dragAndDrop(propogationdrag,notGreedyInner).perform();
        Assert.assertEquals(notGreedyInner.getText(),"Dropped!","Inner (not greedy) drop failed");
        Assert.assertTrue(notGreedyOuter.getText().contains("Dropped!"),"Outer (not greedy) drop failed");
        System.out.println("(non greedy) inner and out text changed to Dropped!");

        actions.dragAndDrop(propogationdrag,greedyIner).perform();
        Assert.assertEquals(greedyIner.getText(),"Dropped!","Inner (greedy) drop failed");
        Assert.assertTrue(greedyOuter.getText().startsWith("Outer droppable"),"Outer (greedy) drop failed");

        System.out.println("(greedy) inner text changed to Dropped!");
    }
}