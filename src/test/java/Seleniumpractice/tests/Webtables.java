package Seleniumpractice.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import Seleniumpractice.DriverSingleton;

import java.time.Duration;
import java.util.List;

public class Webtables {
    WebDriver driver = DriverSingleton.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @Test(priority = 1)
    public void Navigations(){
        driver.get("https://demoqa.com/");
        driver.get("https://demoqa.com/elements");
    }

    @Test(priority = 2)
    public void openviewtables(){
        WebElement webtable = driver.findElement(By.xpath("//div[@class='element-group']//div[text()='Elements']/following::span[text()='Web Tables']"));
        wait.until(ExpectedConditions.visibilityOf(webtable));
        webtable.click();
        System.out.println("Webtable tab has been clicked...");
    }

    @Test(priority = 3)
    public void fetchtabledata(){
        List<WebElement> Header = driver.findElements(By.xpath("//div[@class='rt-thead -header']"));
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']"));
        System.out.println("Fetch webtable data...");

        for(WebElement h:Header){
            System.out.printf(h.getText());
            System.out.println("\n");
        }

        for(WebElement r:rows){
            String rowdata = r.getText();
            System.out.println(rowdata);
            }
        }

}