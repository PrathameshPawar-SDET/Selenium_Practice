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

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Set;
public class uploadsanddownloads {
    WebDriver driver = DriverSingleton.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @Test(priority = 1)
    public void navigateto(){
        driver.get("https://demoqa.com/upload-download");
    }

    @Test(priority = 2)
    public void uploadfile(){
        String filepath = Paths.get(System.getProperty("user.dir"), "sampleFile.jpeg").toString();

        WebElement uploadelement = driver.findElement(By.id("uploadFile"));
        uploadelement.sendKeys(filepath);

        WebElement uploadfilepath = driver.findElement(By.id("uploadedFilePath"));
        System.out.println(uploadfilepath.getText());
    }

    @Test(priority = 3)
    public void downloadFile(){
        WebElement downloadButton = driver.findElement(By.id("downloadButton"));
        downloadButton.click();

        String downloadpath = Paths.get(System.getProperty("user.home"),"/Downloads/sampleFile.jpeg").toString();
        File downloadfile = new File(downloadpath);
        int waitTime = 10;
        if(waitTime>0 && !downloadfile.exists()){
            waitTime--;
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        if(downloadfile.exists()){
            System.out.println("File Downloaded Successfully :"+downloadfile.getAbsolutePath());
        }
        else {
            System.out.println("File download failed");
        }
    }

}