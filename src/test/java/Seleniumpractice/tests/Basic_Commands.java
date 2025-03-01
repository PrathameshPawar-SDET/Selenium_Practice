package Seleniumpractice.tests;

import Seleniumpractice.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class Basic_Commands {

    WebDriver driver = DriverSingleton.getDriver();
    public String url = "https://testingmint.com/";

   @Test
    public void browsercommand(){

       System.out.println("****Running Browser commands****");

       driver.get(url);
       String Currenturl = driver.getCurrentUrl();
       System.out.println("Current url is: "+Currenturl);

       String Title = driver.getTitle();
       System.out.println("Title is: "+Title);

       if(url.equals(Currenturl)){
           System.out.println("Verification is successfull - Correct url is opened");
       }
       else{
           System.out.println("Verification is unsuccessfull - Incorrect url is opened");
           System.out.println("Actual url is: "+url);
           System.out.println("Current url is: "+Currenturl);
       }
   }


   @Test
    public void navigationcommands(){
       System.out.println("****Running Navigations commands****");

       driver.get(url);
       driver.navigate().to("https://testingmint.com/automation-testing/");
       System.out.println("Current url after navigate.to() command: "+driver.getCurrentUrl());
       driver.navigate().back();
       System.out.println("Current url after navigate().back() command: "+driver.getCurrentUrl());
       driver.navigate().forward();
       System.out.println("Current url after navigate().forward() command: "+driver.getCurrentUrl());
   }
}
