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
import java.util.Set;

public class Linktext {
    WebDriver driver = DriverSingleton.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @Test(priority = 1)
    public void naviagtion(){
        driver.get("https://demoqa.com/links");
    }

    @Test(priority = 2)
    public void followlink(){
        String mainwindow = driver.getWindowHandle();
        WebElement simplelink = driver.findElement(By.id("simpleLink"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",simplelink);
        wait.until(ExpectedConditions.visibilityOf(simplelink));
        simplelink.click();

        Set<String> windowHandles = driver.getWindowHandles();
        for(String handle : windowHandles){
            if(!handle.equals(mainwindow)){
                driver.switchTo().window(handle);
                System.out.println("New page title: "+driver.getTitle());
                driver.close();
                driver.switchTo().window(mainwindow);
            }
        }
    }


    @Test(priority = 3)
    public void Apireponse(){

        WebElement created = driver.findElement(By.id("created"));
        wait.until(ExpectedConditions.visibilityOf(created));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",created);
        created.click();

        WebElement responsetext =  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("linkResponse")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",responsetext);
        System.out.println(responsetext.getText());

    }



}