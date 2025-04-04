package Seleniumpractice.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Seleniumpractice.DriverSingleton;

import java.time.Duration;
import java.util.List;
import java.util.Set;
public class datePicker {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://demoqa.com/date-picker");
    }

    @Test(priority = 1)
    public void selectDate(){
        WebElement dateInput = driver.findElement(By.id("datePickerMonthYearInput"));
        dateInput.click();

        WebElement monthdropdown = driver.findElement(By.className("react-datepicker__month-select"));
        Select selectmonth = new Select(monthdropdown);
        selectmonth.selectByVisibleText("May");

        WebElement yeardropdown = driver.findElement(By.className("react-datepicker__year-select"));
        Select selectyear = new Select(yeardropdown);
        selectyear.selectByVisibleText("2005");

        WebElement dateSelect = driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='15']"));
        dateSelect.click();

        System.out.println("Selected Date :"+driver.findElement(By.id("datePickerMonthYearInput")).getDomAttribute("value"));
    }

    @Test(priority = 2)
    public void selectDateandTime(){
        WebElement dateandtimeinput = driver.findElement(By.id("dateAndTimePickerInput"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",dateandtimeinput);
        dateandtimeinput.click();

        WebElement monthdropdown = driver.findElement(By.className("react-datepicker__month-read-view"));
        monthdropdown.click();

        WebElement monthoption = driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__month-option') and text()='May']"));
        monthoption.click();

        WebElement yearDropdown = driver.findElement(By.className("react-datepicker__year-read-view"));
        yearDropdown.click();

        WebElement yearoption = driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__year-option') and text()='2020']"));
        yearoption.click();


        WebElement day = driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='10']"));
        day.click();

        WebElement time = driver.findElement((By.xpath("//li[contains(@class,'react-datepicker__time-list-item') and text()='12:30']")));
        time.click();
        System.out.println("Selected Date and time :"+driver.findElement(By.id("dateAndTimePickerInput")).getDomAttribute("value"));


    }



}