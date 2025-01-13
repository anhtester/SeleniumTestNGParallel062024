package com.anhtester.common;

import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    @BeforeSuite
    public void setupEnvironment(){
        PropertiesHelper.loadAllFiles();
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver;

        if(PropertiesHelper.getValue("BROWSER").isEmpty() && PropertiesHelper.getValue("BROWSER").isBlank()){
            browser = browser;
        }else {
            browser = PropertiesHelper.getValue("BROWSER");
        }

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                System.out.println("Khởi chạy trình duyệt Chrome...");
                break;
            case "edge":
                driver = new EdgeDriver();
                System.out.println("Khởi chạy trình duyệt Edge...");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                System.out.println("Khởi chạy trình duyệt Firefox...");
                break;
            case "safari":
                driver = new SafariDriver();
                System.out.println("Khởi chạy trình duyệt Safari...");
                break;
            default:
                driver = new ChromeDriver();
                System.out.println("Khởi chạy trình duyệt Chrome mặc định...");
                break;
        }

        DriverManager.setDriver(driver); //Set giá trị driver vào trong ThreadLocal

        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    @AfterMethod
    public void closeDriver() {

        CaptureHelper.stopRecord();

        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }
}
