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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import java.util.List;
public class BrokenLinks {
    WebDriver driver = DriverSingleton.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @Test(priority = 1)
    public void navigation(){
        driver.get("https://demoqa.com/broken");
    }

    public static boolean isBroken(String urlstring){
        try{
            URL url = new URL(urlstring);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int responsecode = connection.getResponseCode();
            return responsecode>=400;

        }
        catch (IOException e){
            return true;
        }
    }

    @Test(priority = 2)
    public void brokenImages(){
        List<WebElement> images = driver.findElements(By.tagName("img"));
        for(WebElement imgage : images){
            String imageurl = imgage.getDomAttribute("src");
            if(imageurl!=null){
                if(isBroken(imageurl)){
                    System.out.println("Broken Image :" +imageurl);
                }
                else{
                    System.out.println("Valid Image :"+imageurl);
                }
            }
        }
    }


    @Test(priority = 3)
    public void brokenLinks(){
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link:links){
            String linksurl = link.getDomAttribute("href");
            if(linksurl!=null){
                if(isBroken(linksurl)){
                    System.out.println("Broken Link :"+linksurl);
                }
                else {
                    System.out.println("Valid Link :"+linksurl);
                }
            }
        }
    }
}