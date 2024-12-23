package com.anhtester.common;

import com.anhtester.drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver;

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
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }
}
