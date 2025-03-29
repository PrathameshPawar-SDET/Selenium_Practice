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

public class practiceForm {
    WebDriver driver = DriverSingleton.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @BeforeClass
    public void navigation(){
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @Test
    public void fillform(){

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("Prathamesh");

        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Pawar");

        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("test@gmail.com");

        WebElement gender = driver.findElement(By.cssSelector("label[for='gender-radio-1']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",gender);

        WebElement mobileNumber = driver.findElement(By.id("userNumber"));
        mobileNumber.sendKeys("1234567890");

        WebElement dateofBirth = driver.findElement(By.id("dateOfBirthInput"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",dateofBirth);
        dateofBirth.click();

        WebElement yearselector = driver.findElement(By.cssSelector(".react-datepicker__year-select"));
        yearselector.sendKeys("1998");

        WebElement monthSelector = driver.findElement(By.cssSelector(".react-datepicker__month-select"));
        monthSelector.sendKeys("July");

        WebElement dateselector = driver.findElement(By.xpath("//div[text()='1']"));
        dateselector.click();

        WebElement subjectInput = driver.findElement(By.id("subjectsInput"));
        subjectInput.sendKeys("Maths");
        subjectInput.sendKeys(Keys.ENTER);

        WebElement hobbies = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']"));
        hobbies.click();

        WebElement filupload = driver.findElement(By.id("uploadPicture"));
        File file = new File("C:\\Users\\user\\Downloads\\sampleFile.jpeg");
        filupload.sendKeys(file.getAbsolutePath());

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("Building number, City, State, Country, Pincode");

        Actions act = new Actions(driver);
        driver.findElement(By.id("state")).click();

        WebElement state = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='NCR']")));
        act.moveToElement(state).click().perform();

        driver.findElement(By.id("city")).click();
        WebElement city = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Delhi']")));
        act.moveToElement(city).click().perform();

        WebElement submit = driver.findElement(By.id("submit"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",submit);

        WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));
        Assert.assertEquals(modalTitle.getText(),"Thanks for submitting the form","Form submission failed");

        try {
            takeScreenShot("Formsubmission-success.png");
        }
        catch (IOException e){
            System.out.println("Failed to capture screenshot :"+e.getMessage());
        }


        validateData("Student Name","Prathamesh Pawar");
        validateData("Student Email","test@gmail.com");
        validateData("Gender","Male");
        validateData("Mobile","1234567890");
        validateData("Date of Birth","01 July,1998");
        validateData("Subjects","Maths");
        validateData("Hobbies","Sports");
        validateData("Picture","sampleFile.jpeg");
        validateData("Address","Building number, City, State, Country, Pincode");
        validateData("State and City","NCR Delhi");



    }


    public void validateData(String field, String expectedvalue){
        String actualvalue = driver.findElement(By.xpath("//td[text()='"+field+"']/following-sibling::td")).getText();
        Assert.assertEquals(actualvalue,expectedvalue,"Mismatch in "+field);
        System.out.println("Verified "+field+ " -> "+actualvalue);

    }

    public void takeScreenShot(String fileName) throws IOException {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,new File(fileName));
        System.out.println("Screenshot saved as :"+fileName);
    }

}