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
public class draggable{

    WebDriver driver;
    JavascriptExecutor js;
    Actions actions;

    @FindBy(id = "draggableExample-tab-simple")
    WebElement simpleTab;

    @FindBy(css = "#draggableExample-tabpane-simple #dragBox")
    WebElement simpleDrag;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);
        driver.get("https://demoqa.com/dragabble");
    }

    @Test(priority = 1)
    public void testSimpleDrag() throws InterruptedException{
        js.executeScript("arguments[0].scrollIntoView();",simpleTab);
        simpleTab.click();
        Point initialLocation = simpleDrag.getLocation();
        System.out.println("Initial location of Drag Me Button : "+initialLocation);
        actions.dragAndDropBy(simpleDrag,100,50).perform();
        Thread.sleep(1000);
        Point finalLocation = simpleDrag.getLocation();
        System.out.println("Final location of Drag Me Button : "+finalLocation);
        Assert.assertNotEquals(initialLocation,finalLocation,"Position of Drag me button did not change");
    }
}