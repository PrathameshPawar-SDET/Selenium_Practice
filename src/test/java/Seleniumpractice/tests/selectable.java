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
public class selectable {

    WebDriver driver;
    JavascriptExecutor js;
    Actions actions;

    @FindBy(id = "demo-tab-list")
    WebElement demoTablist;

    @FindBy(id = "demo-tab-grid")
    WebElement demoTabgrid;

    @FindBy(css = "#demo-tabpane-list .list-group-item")
    List<WebElement> listItems;

    @FindBy(css = "#demo-tabpane-grid .list-group-item")
    List<WebElement> gridItems;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
        driver.get("https://demoqa.com/selectable");
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    @Test(priority = 1)
    public void selecListItem(){
        js.executeScript("arguments[0].scrollIntoView();",demoTablist);
        demoTablist.click();

        WebElement itemtoselect = listItems.get(2);
        String text = itemtoselect.getText();
        System.out.println("Selected item from List : "+text);
        itemtoselect.click();

        Assert.assertTrue(itemtoselect.getDomAttribute("class").contains("active"),"Item " +text+ " is not marked as active after clicking");

    }
}