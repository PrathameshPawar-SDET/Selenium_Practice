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

public class sortable{

    WebDriver driver;
    Actions actions;
    JavascriptExecutor js;

    @FindBy(id = "demo-tab-list")
    WebElement demoTablist;

    @FindBy(id = "demo-tab-grid")
    WebElement demoTabgrid;

    @FindBy(css = "#demo-tabpane-list div[class*='list-group-item']")
    List<WebElement> listItem;

    @FindBy(css = "#demo-tabpane-grid div[class*='list-group-item']")
    List<WebElement> gridItem;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
        driver.get("https://demoqa.com/sortable");
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    @Test(priority = 1)
    public void testListSorting(){
        js.executeScript("arguments[0].scrollIntoView();",demoTablist);
        demoTablist.click();

        List<String> beforesort = new ArrayList<>();
        for(WebElement item : listItem){
            beforesort.add(item.getText());
        }
        System.out.println(beforesort);

        actions.clickAndHold(listItem.get(0)).moveToElement(listItem.get(3)).release().perform();

        List<String> aftersort = new ArrayList<>();
        for(WebElement item : driver.findElements(By.cssSelector("#demo-tabpane-list div[class*='list-group-item']"))){
            aftersort.add((item.getText()));
        }
        System.out.println(aftersort);

        Assert.assertNotEquals(beforesort,aftersort,"List order does not change after drag and drop");
    }


}