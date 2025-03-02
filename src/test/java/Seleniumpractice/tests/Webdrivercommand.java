package Seleniumpractice.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import Seleniumpractice.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.util.List;


import java.time.Duration;

public class Webdrivercommand {
    WebDriver driver = DriverSingleton.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @Test(priority = 1)
    public void Navigations(){
        driver.get("https://demoqa.com/");
        driver.navigate().to("https://demoqa.com/elements");
    }

//    @Test(priority = 2)
    public void TextboxInteraction(){

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

//    @Test(priority = 3)
    public void CheckboxInteraction() throws InterruptedException{

        WebElement checkboxTab = driver.findElement(By.xpath("//div[@class='element-group']//div[text()='Elements']/following::span[text()='Check Box']"));
        wait.until(ExpectedConditions.elementToBeClickable(checkboxTab));
        checkboxTab.click();

        WebElement Expandall = driver.findElement(By.xpath("//button[@class='rct-option rct-option-expand-all']"));
        Expandall.click();


        String[] Checboxesvalue={"Desktop", "Downloads"};
        for(int i=0;i<Checboxesvalue.length;i++){
            System.out.println(Checboxesvalue[i]);
            String checkboxDynamicxpath = "//span[@class='rct-title' and text()='"+Checboxesvalue[i]+"']/preceding-sibling::span[@class='rct-checkbox']";
            System.out.println(checkboxDynamicxpath);
            WebElement checkboxelement = driver.findElement(By.xpath(checkboxDynamicxpath));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",checkboxelement);
            wait.until(ExpectedConditions.visibilityOf(checkboxelement));
            checkboxelement.click();
            Thread.sleep(3000);
        }

        List<WebElement> SelectedCheckbox = driver.findElements(By.xpath("//div[@id='result']//span[@class='text-success']"));
        System.out.println("Following are selected checkboxes:");
        for(WebElement sc:SelectedCheckbox){
            System.out.println(sc.getText());
        }
    }

    @Test(priority = 4)
    public void radiobutton(){
        WebElement radiobuttonTab = driver.findElement(By.xpath("//div[@class='element-group']//div[text()='Elements']/following::span[text()='Radio Button']"));
        wait.until(ExpectedConditions.elementToBeClickable(radiobuttonTab));
        radiobuttonTab.click();

        List<WebElement> Radiobuttons = driver.findElements(By.xpath("//input[@type='radio']/following::label"));

        WebElement SelectedValue;
        for(WebElement rb:Radiobuttons){
            if(rb.isEnabled()){
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",rb);
                rb.click();
                SelectedValue = driver.findElement(By.xpath("//span[@class='text-success']"));

                System.out.println("You have selected: "+SelectedValue.getText());
            }else{

                System.out.println(rb.getText()+ "is disabled");
            }
        }


    }

}