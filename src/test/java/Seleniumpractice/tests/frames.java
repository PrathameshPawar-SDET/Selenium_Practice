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
public class frames {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.navigate().to("https://demoqa.com/frames");

    }

    @Test(priority = 1)
    public void firstIframe(){
        driver.switchTo().frame("frame1");
        WebElement heading = driver.findElement(By.id("sampleHeading"));
        String frameText = heading.getText();

        Assert.assertEquals(frameText,"This is a sample page","frame1 content is mismatched");
        driver.switchTo().defaultContent();
        System.out.println("Successfully switched to frame1 and content is verified");
    }

    @Test(priority = 2)
    public void secondFrame(){
        WebElement frame2 = driver.findElement(By.id("frame2"));
        driver.switchTo().frame(frame2);
        WebElement heading = driver.findElement(By.id("sampleHeading"));
        String frametext2 = heading.getText();
        Assert.assertEquals(frametext2,"This is a sample page","frame2 content is mismatched");
        driver.switchTo().defaultContent();
        System.out.println("Successfully switched to frame2 and content is verified");

    }

    @Test(priority = 3)
    public void countframes(){
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        System.out.println("Total frames on page :"+frames.size());

    }
}