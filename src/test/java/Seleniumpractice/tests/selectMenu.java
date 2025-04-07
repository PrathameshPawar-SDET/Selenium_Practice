package Seleniumpractice.tests;

import Seleniumpractice.DriverSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class selectMenu {

    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    @FindBy(id = "withOptGroup")
    WebElement selectValuedropdown;

    @FindBy(xpath ="//div[text()='Group 2, option 1']")
    WebElement Valuefromgroupdropdown;

    @FindBy(className = "css-1uccc91-singleValue")
    WebElement SelectedValuetext;

    @FindBy(id = "selectOne")
    WebElement selectOnedropdown;

    @FindBy(xpath = "//div[text()='Dr.']")
    WebElement drOption;

    @FindBy(xpath = "//div[@id='selectOne']//div[contains(@class,'css-1uccc91-singleValue')]")
    WebElement oneDropdownText;

    @FindBy(id = "oldSelectMenu")
    WebElement oldSelectMenu;

    @FindBy(xpath = "//div[text()='Select...']")
    WebElement multiselectdropdown;

    @FindBy(id = "cars")
    WebElement standardmultiselect;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        driver.get("https://demoqa.com/select-menu");
        PageFactory.initElements(driver,this);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    }

    @Test(priority = 1)
    public void selectValuewithgroup(){
        js.executeScript("arguments[0].scrollIntoView();",selectValuedropdown);
        selectValuedropdown.click();
        wait.until(ExpectedConditions.visibilityOf(Valuefromgroupdropdown));
        Valuefromgroupdropdown.click();
        System.out.println("Selected Value on Group-Option Dropdown : "+SelectedValuetext.getText());
    }

    @Test(priority = 2)
    public void SelectOneoptionTest(){
        selectOnedropdown.click();
        drOption.click();
        Assert.assertEquals(oneDropdownText.getText(),"Dr.","Selected Value not Matched");
        System.out.println("Selected value on one dropdown :"+oneDropdownText.getText());
    }

    @Test(priority = 3)
    public void oldStyleSelectMenu(){
        Select sel = new Select(oldSelectMenu);
        sel.selectByVisibleText("Blue");
        String selectedText = sel.getFirstSelectedOption().getText();
        System.out.println("Selected value on old Style menu : "+selectedText);
    }

    @Test(priority = 4)
    public void multiselectDropdown() {
        multiselectdropdown.click();
        multiselectdropdown.findElement(By.xpath("//div[text()='Blue']")).click();
        multiselectdropdown.findElement(By.xpath("//div[text()='Red']")).click();
        List<WebElement> selectedOptions = driver.findElements(By.xpath("//div[@class='css-12jo7m5']"));
        System.out.println("Selected options :");
        for (int i = 0; i < selectedOptions.size(); i++) {
            System.out.println(selectedOptions.get(i).getText());
        }
    }

    @Test(priority = 5)
    public void standardmultiselect(){
        Select sel = new Select(standardmultiselect);
        Assert.assertTrue(sel.isMultiple(),"Dropdown is not multiselectable");
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).click(sel.getOptions().get(0)).click(sel.getOptions().get(3)).keyUp(Keys.CONTROL).build().perform();
        boolean volvoSelected = sel.getAllSelectedOptions().stream().anyMatch(opt -> opt.getText().equals("Volvo"));
        boolean audiSelected = sel.getAllSelectedOptions().stream().anyMatch(opt -> opt.getText().equals("Audi"));
        Assert.assertTrue(volvoSelected,"Volvo not selected");
        Assert.assertTrue(audiSelected,"Audi not selected");
        System.out.println("Selected options are : "+sel.getAllSelectedOptions().get(0).getText()+ " and " +sel.getAllSelectedOptions().get(1).getText());
    }
}