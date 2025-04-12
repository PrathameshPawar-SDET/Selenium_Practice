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

    //Simple Tab

    @FindBy(id = "draggableExample-tab-simple")
    WebElement simpleTab;

    @FindBy(css = "#draggableExample-tabpane-simple #dragBox")
    WebElement simpleDrag;

    //Axis Restricted Tab

    @FindBy(id = "draggableExample-tab-axisRestriction")
    WebElement axisTab;

    @FindBy(id = "restrictedX")
    WebElement axisX;

    @FindBy(id = "restrictedY")
    WebElement axisY;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);
        driver.get("https://demoqa.com/dragabble");
    }

//    @Test(priority = 1)
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

    @Test(priority = 2)
    public void testAxisRestricted() throws InterruptedException{
        js.executeScript("arguments[0].scrollIntoView();",axisTab);
        axisTab.click();
        Point initialLocationX = axisX.getLocation();
        System.out.println("Initial location of Only-X button : "+initialLocationX);
        actions.dragAndDropBy(axisX,100,50).perform();
        Thread.sleep(1000);
        Point finalLocationX = axisX.getLocation();
        System.out.println("Final location of Only-X button : "+finalLocationX);
        Assert.assertNotEquals(initialLocationX.getX(),finalLocationX.getX(),"X axis should be changed for Only-X");
        Assert.assertEquals(initialLocationX.getY(),finalLocationX.getY(),"Y axis should not change of Only-X");


        Point initialLocationy = axisY.getLocation();
        System.out.println("Initial location of Only-Y button : "+initialLocationy);
        actions.dragAndDropBy(axisY,100,50).perform();
        Thread.sleep(1000);
        Point finalLocationY = axisY.getLocation();
        System.out.println("Final location of Only-Y button : "+finalLocationY);
        Assert.assertNotEquals(initialLocationy.getY(),finalLocationY.getY(),"Y axis should be changed for Only-Y");
        Assert.assertEquals(initialLocationy.getX(),finalLocationY.getX(),"X axis should not change of Only-Y");
    }
}