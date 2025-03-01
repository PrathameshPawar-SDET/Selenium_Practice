package Seleniumpractice.tests;

import Seleniumpractice.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class Basic_Commands {

    WebDriver driver = DriverSingleton.getDriver();

   @Test
    public void browsercommand(){

       driver.get("https://testingmint.com/");
       String Currenturl = driver.getCurrentUrl();
       System.out.println("Current url is: "+Currenturl);

   }
}
