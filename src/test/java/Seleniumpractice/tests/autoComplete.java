package Seleniumpractice.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Seleniumpractice.DriverSingleton;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class autoComplete {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://demoqa.com/auto-complete");
    }

    @Test(priority = 1)
    public void handleMultipleColor(){
            WebElement inputBox = driver.findElement(By.id("autoCompleteMultipleInput"));
            inputBox.sendKeys("B");
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",inputBox);
            List<WebElement> Suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".auto-complete__menu div")));


            if(!Suggestions.isEmpty()){
                Suggestions.get(0).click();
            }
            WebElement SelectedValue = driver.findElement(By.className("auto-complete__multi-value__label"));
            System.out.println("Selected Color :"+SelectedValue.getText());
            Assert.assertFalse(SelectedValue.getText().isEmpty(),"No color was selected");

            inputBox.sendKeys("M");
            Suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".auto-complete__menu div")));
            if (!Suggestions.isEmpty()){
                Suggestions.get(0).click();
            }

            List<WebElement> Selectedcolor = driver.findElements(By.className("auto-complete__multi-value__label"));
            System.out.println("Selected Colors :"+Selectedcolor.size());
            Assert.assertTrue(Selectedcolor.size()>1,"Second color was not selected");

    }


    @Test(priority = 2)
    public void handleSingleColor(){
        WebElement singleColorInput = driver.findElement(By.id("autoCompleteSingleInput"));
        singleColorInput.sendKeys("I");
        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".auto-complete__menu div")));
        suggestion.click();

        WebElement selectValue = driver.findElement(By.className("auto-complete__single-value"));
        System.out.println("Selected Single Color :"+selectValue.getText());
        Assert.assertFalse(selectValue.getText().isEmpty(),"No color was selected");
    }
}