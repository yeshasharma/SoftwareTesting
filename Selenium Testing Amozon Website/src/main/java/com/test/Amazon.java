package com.test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Amazon {
     public String url;
     private static final String TIMESTAMP = "2022-06-05T05:01:33.3649536Z";

    public String BrowseSmileAmazon() throws IOException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yesha\\OneDrive\\selenium-java-4.2.1\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.out.println("Setting Up driver and window Size"+ TIMESTAMP);

        driver.get("https://smile.amazon.com/");
        File amazon = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(amazon, new File("C:\\Users\\yesha\\OneDrive\\Selenium\\snapshots\\OpenSmileAmazon.png"));
        System.out.println("Opening Amazon website"+ TIMESTAMP);

        try {
            if (driver.getCurrentUrl() == "https://smile.amazon.com/charity?ref_=smi_ge2_ul_si_rl&ein=&") {
                driver.findElement(By.linkText("Sign out")).click();
                driver.get("https://www.smile.amazon.com");
                File ifLogin = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(ifLogin, new File("C:\\Users\\yesha\\OneDrive\\Selenium\\snapshots\\SmileLoggedInUser.png"));
                System.out.println("If the user is logged in"+ TIMESTAMP);
            }
            else {
                driver.findElement(By.linkText("Learn more about AmazonSmile")).click();
                url = driver.getCurrentUrl();
                File returnUrl = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(returnUrl, new File("C:\\Users\\yesha\\OneDrive\\Selenium\\snapshots\\Url.png"));
                System.out.println("If the user is not logged in and continue"+ TIMESTAMP);
            }

        } catch (Exception e) {
            System.out.println("In Catch block");
        }
        return url;
    }

    public static void main(String[] args) throws IOException {
        Amazon amaze = new Amazon();
        String url = amaze.BrowseSmileAmazon();
    }
}
