package Seleniumpractice.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Seleniumpractice.DriverSingleton;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.logging.FileHandler;

public class browserWindows {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.navigate().to("https://demoqa.com/browser-windows");
    }

    @Test(priority = 1)
    public void testnewtab(){
        WebElement newTab = driver.findElement(By.id("tabButton"));
        newTab.click();
        String mainwindow = driver.getWindowHandle();
        switchwindowhandle(mainwindow);
        WebElement Header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sampleHeading")));
        Assert.assertEquals(Header.getText(),"This is a sample page","Tab Content Mismatched");
        driver.close();
        driver.switchTo().window(mainwindow);
    }

    @Test(priority = 2)
    public void testnewWindow(){
        WebElement newWindow = driver.findElement(By.id("windowButton"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",newWindow);
        newWindow.click();
        String mainWindow = driver.getWindowHandle();
        switchwindowhandle(mainWindow);
        WebElement Header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sampleHeading")));
        Assert.assertEquals(Header.getText(),"This is a sample page","Tab Content Mismatched");
        driver.close();
        driver.switchTo().window(mainWindow);

    }

    @Test(priority = 3)
    public void newWindowMessage(){
        WebElement newWindow = driver.findElement(By.id("messageWindowButton"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",newWindow);
        newWindow.click();
        String mainWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        switchwindowhandle(mainWindow);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        String text = driver.findElement(By.tagName("body")).getText().trim();
        Assert.assertTrue(text.contains("Knowledge increases by sharing"),"Message window content mismatch");
        driver.close();
        driver.switchTo().window(mainWindow);
    }

    public void switchwindowhandle(String mainwindow){
        Set<String> allwindows = driver.getWindowHandles();
        for (String window : allwindows){
            if(!window.equals(mainwindow)){
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void takeScreenshot(String filename){
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile,new File("screenshot/" +filename+ ".png"));
            System.out.println("Screenshot taken: screenshot/"+filename+ ".png");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}