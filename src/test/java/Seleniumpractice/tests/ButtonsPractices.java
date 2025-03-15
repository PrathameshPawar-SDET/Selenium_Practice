package Seleniumpractice.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import Seleniumpractice.DriverSingleton;

import java.time.Duration;

public class ButtonsPractices {

    WebDriver driver = DriverSingleton.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @Test(priority = 1)
    public void Navigations(){
        driver.navigate().to("https://demoqa.com/buttons");
    }

    @Test(priority = 2)
    public void doubleClickbutton(){
        Actions act = new Actions(driver);

        //Doublick Click Button
        WebElement doubleClick = driver.findElement(By.id("doubleClickBtn"));
        wait.until(ExpectedConditions.visibilityOf(doubleClick));
        act.doubleClick(doubleClick).perform();

        WebElement doubleclickmessage = driver.findElement(By.id("doubleClickMessage"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",doubleclickmessage);
        wait.until(ExpectedConditions.visibilityOf(doubleclickmessage));
        System.out.println(doubleclickmessage.getText());

        //Right Click Button
        WebElement rightclick = driver.findElement(By.id("rightClickBtn"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",rightclick);
        wait.until(ExpectedConditions.visibilityOf(rightclick));
        act.contextClick(rightclick).perform();

        WebElement righclickmessage = driver.findElement(By.id("rightClickMessage"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",righclickmessage);
        wait.until(ExpectedConditions.visibilityOf(righclickmessage));
        System.out.println(righclickmessage.getText());


    }
}