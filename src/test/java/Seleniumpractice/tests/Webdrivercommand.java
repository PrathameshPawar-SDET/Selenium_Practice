package Seleniumpractice.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Seleniumpractice.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Webdrivercommand {
    WebDriver driver = DriverSingleton.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @Test
    public void WebDriverCommands() throws InterruptedException{
        driver.get("https://demoqa.com/");
        driver.navigate().to("https://demoqa.com/elements");
        WebElement Textboxtab = driver.findElement(By.xpath("//div[@class='element-group']//div[text()='Elements']/following::li[@id='item-0'][1]"));
        wait.until(ExpectedConditions.visibilityOf(Textboxtab));
        Textboxtab.click();

        WebElement fullName = driver.findElement(By.id("userName"));
        fullName.sendKeys("Prathamesh");
        WebElement Email = driver.findElement(By.id("userEmail"));
        Email.sendKeys("test@gmail.com");
        WebElement currendAdd = driver.findElement(By.id("currentAddress"));
        currendAdd.sendKeys("Current Address for testing the form");
        WebElement permanentAdd = driver.findElement(By.id("permanentAddress"));
        permanentAdd.sendKeys("Permanent address for testing the form");
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit']"));
        submitButton.click();

    }

}