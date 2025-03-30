package Seleniumpractice.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Seleniumpractice.DriverSingleton;

import java.time.Duration;
public class alerts {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.navigate().to("https://demoqa.com/alerts");
    }

    @Test(priority = 1)
    public void handleSimpleAlert(){
        WebElement alertbuttoun = driver.findElement(By.id("alertButton"));
        alertbuttoun.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"You clicked a button", "Alert message mismatched");
        alert.accept();
        System.out.println("Simple alert handled successfully");
    }

    @Test(priority = 2)
    public void handleDelayedAlert(){
        WebElement timeralert = driver.findElement(By.id("timerAlertButton"));
        timeralert.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"This alert appeared after 5 seconds", "Alert message mimathced");
        alert.accept();
        System.out.println("Timer alert handeled successfully");
    }

    @Test(priority = 3)
    public void handleConfirmAlert(){
        WebElement confirmalert = driver.findElement(By.id("confirmButton"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",confirmalert);

        confirmalert.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"Do you confirm action?","Confirmation alert message mismatched");
        alert.dismiss();
        WebElement confirmResult = driver.findElement(By.id("confirmResult"));
        Assert.assertEquals(confirmResult.getText(),"You selected Cancel","Confirmation result mismtached");
        System.out.println("Confirmation alert handled successfully");
    }

    @Test(priority = 4)
    public void handlePromptAlert(){
        WebElement promtButton = driver.findElement(By.id("promtButton"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",promtButton);
        promtButton.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"Please enter your name","Prompt alert message mismatched");
        alert.sendKeys("Prathamesh Pawar");
        alert.accept();
        WebElement promptResult = driver.findElement(By.id("promptResult"));
        Assert.assertTrue(promptResult.getText().contains("Prathamesh Pawar"),"Prompt result mismathced");
        System.out.println("Prompt alert handled successfully");
    }
}