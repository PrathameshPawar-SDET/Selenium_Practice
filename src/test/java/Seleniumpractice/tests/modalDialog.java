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
import java.util.Set;

public class modalDialog {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://demoqa.com/modal-dialogs");
    }

    @Test(priority = 1)
    public void smallModal(){
        WebElement smallmodalbutton = driver.findElement(By.id("showSmallModal"));
        smallmodalbutton.click();

        WebElement heading = driver.findElement(By.id("example-modal-sizes-title-sm"));
        System.out.println(heading.getText());
        WebElement body = driver.findElement(By.className("modal-body"));
        System.out.println(body.getText());

        Assert.assertEquals(heading.getText(),"Small Modal","Small Modal Title Mismatched");
        Assert.assertTrue(body.getText().contains("This is a small modal"),"Small modal content mismatched");

        driver.findElement(By.id("closeSmallModal")).click();
    }

    @Test(priority = 2)
    public void largeModal(){
        WebElement showLargeModal = driver.findElement(By.id("showLargeModal"));
        showLargeModal.click();
        WebElement modalTitle = driver.findElement(By.id("example-modal-sizes-title-lg"));
        System.out.println(modalTitle.getText());

        WebElement modalBody = driver.findElement(By.xpath("//div[@class='modal-body']/p"));
        System.out.println(modalBody.getText());

        Assert.assertEquals(modalTitle.getText(),"Large Modal","Large Modal Title Mismatched");
        Assert.assertTrue(modalBody.getText().contains("Lorem Ipsum is simply dummy text"),"Large modal content is mimatched");

        driver.findElement(By.id("closeLargeModal")).click();
    }
}