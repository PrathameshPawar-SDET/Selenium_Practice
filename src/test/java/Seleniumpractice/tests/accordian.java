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
public class accordian {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://demoqa.com/accordian");
    }

    @Test(priority = 1)
    public void firstAccordian(){
        WebElement accordiancontent;
        WebElement activeAccordian = driver.findElement(By.id("section1Content"));
        if(activeAccordian.isDisplayed()){
//            System.out.println("If condition is running");
            accordiancontent = driver.findElement(By.cssSelector("#section1Content p"));

        }
        else {
//            System.out.println("Else condition is running");
            WebElement accordianheading = driver.findElement(By.id("section1Heading"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",accordianheading);
            accordianheading.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#section1Content p")));
            accordiancontent = driver.findElement(By.cssSelector("#section1Content p"));

        }
        System.out.println(accordiancontent.getText().substring(0,50)+"...");
        Assert.assertTrue(accordiancontent.getText().contains("Lorem Ipsum is simply dummy text of the printing and typesetting industry"),"Accordian 1 Content Does not matched");
    }

    @Test(priority = 2)
    public void secondAccordian(){
        WebElement accordiancontent;
        WebElement activeAccordian = driver.findElement(By.id("section2Content"));
        if(activeAccordian.isDisplayed()){
//            System.out.println("If condition is running");
            accordiancontent = driver.findElement(By.cssSelector("#section2Content p"));

        }
        else {
//            System.out.println("Else condition is running");
            WebElement accordianheading = driver.findElement(By.id("section2Heading"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",accordianheading);
            accordianheading.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#section2Content p")));
            accordiancontent = driver.findElement(By.cssSelector("#section2Content p"));

        }
        System.out.println(accordiancontent.getText().substring(0,50)+"...");
        Assert.assertTrue(accordiancontent.getText().contains("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock"),"Accordian 2 Content Does not matched");
    }

    @Test(priority = 3)
    public void thirdAccordian(){
        WebElement accordiancontent;
        WebElement activeAccordian = driver.findElement(By.id("section3Content"));
        if(activeAccordian.isDisplayed()){
//            System.out.println("If condition is running");
            accordiancontent = driver.findElement(By.cssSelector("#section3Content p"));

        }
        else {
//            System.out.println("Else condition is running");
            WebElement accordianheading = driver.findElement(By.id("section3Heading"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",accordianheading);
            accordianheading.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#section3Content p")));
            accordiancontent = driver.findElement(By.cssSelector("#section3Content p"));

        }
        System.out.println(accordiancontent.getText().substring(0,50)+"...");
        Assert.assertTrue(accordiancontent.getText().contains("It is a long established fact that a reader will be distracted by the readable content of a page"),"Accordian 3 Content Does not matched");
    }

    @Test(priority = 4)
    public void totalnumberofaccordians(){
        List<WebElement> accordiancount = driver.findElements(By.xpath("//div[@class='card']"));
        System.out.println("Total count of accordians :"+accordiancount.size());
        Assert.assertTrue(accordiancount.size()>0,"No accordian found on this page");
    }
}