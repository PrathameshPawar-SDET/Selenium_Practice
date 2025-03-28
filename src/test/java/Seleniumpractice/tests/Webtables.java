package Seleniumpractice.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import Seleniumpractice.DriverSingleton;

import javax.swing.*;
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

        @Test(priority = 4)
        public void addNewRecord(){

        WebElement addbutton = driver.findElement(By.id("addNewRecordButton"));
        addbutton.click();
        WebElement registationModal = driver.findElement(By.id("registration-form-modal"));
        wait.until(ExpectedConditions.visibilityOf(registationModal));
        System.out.println("Registration Modal is Displayed...");

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("Prathamesh");

        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Pawar");

        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("test@gmail.com");

        WebElement age = driver.findElement(By.id("age"));
        age.sendKeys("26");

        WebElement salary = driver.findElement(By.id("salary"));
        salary.sendKeys("30000");

        WebElement department = driver.findElement(By.id("department"));
        department.sendKeys("Software Testing");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        fetchtabledata();
        }


        @Test(priority = 5)
        public void editRecord(){
        WebElement editicon = driver.findElement(By.id("edit-record-1"));
        wait.until(ExpectedConditions.visibilityOf(editicon));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",editicon);
        editicon.click();
        WebElement registationModal = driver.findElement(By.id("registration-form-modal"));
        wait.until(ExpectedConditions.visibilityOf(registationModal));

        WebElement department = driver.findElement(By.id("department"));
        System.out.println("Department name before updating :" +department.getDomAttribute("value"));

        department.clear();
        department.sendKeys("CEO");
        System.out.println("Department name after updating :" +department.getDomAttribute("value"));
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        }

        @Test(priority = 6)
        public void deleteRecord(){
        System.out.println("Rows before deleting the records...");
        fetchtabledata();
        WebElement deleteicon = driver.findElement(By.id("delete-record-2"));
        wait.until(ExpectedConditions.visibilityOf(deleteicon));
        deleteicon.click();
        System.out.println("Rows after deleting the record...");
        fetchtabledata();

        }

        @Test(priority = 7)
        public void searchData(){
        WebElement Searchbox = driver.findElement(By.id("searchBox"));
        wait.until(ExpectedConditions.visibilityOf(Searchbox));
        Searchbox.sendKeys("Alden");
        fetchtabledata();
        }
}