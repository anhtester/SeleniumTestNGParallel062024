package com.anhtester.Bai30_Screenshot_Video.testcases;

import com.anhtester.Bai30_Screenshot_Video.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.dataproviders.DataProviderFactory;
import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.CaptureHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test(priority = 1, dataProvider = "data_provider_login_success", dataProviderClass = DataProviderFactory.class)
    public void loginSuccess(String email, String password) {
        CaptureHelper.startRecord("loginSuccess");

        loginPage = new LoginPage();
        loginPage.loginCRM(email, password);
        CaptureHelper.captureScreenshot("loginSuccess");

        // Chụp ảnh toàn bộ màn hình bằng Ashot (thư viện ngoài)
//        Screenshot screenshot = new AShot()
//                .shootingStrategy(ShootingStrategies.viewportPasting(100)) // Scroll từng phần để ghép ảnh
//                .takeScreenshot(DriverManager.getDriver());
//        try {
//            ImageIO.write(screenshot.getImage(), "PNG", new File("fullpage_screenshot.png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // Chụp toàn màn hình (bao gồm Taskbar) bằng Robot class (Java)
//        Robot robot = null;
//        try {
//            robot = new Robot();
//        } catch (AWTException e) {
//            throw new RuntimeException(e);
//        }
//        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//        BufferedImage fullScreenImage = robot.createScreenCapture(screenRect);
//        try {
//            ImageIO.write(fullScreenImage, "PNG", new File("full_screen_with_taskbar.png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        //WebUI.sleep(1);
        CaptureHelper.stopRecord(1);

        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 1, dataProvider = "data_provider_login_excel", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessFromDataProvider(String email, String password) {
        CaptureHelper.startRecord("loginSuccessFromDataProvider");

        loginPage = new LoginPage();
        loginPage.loginCRM(email, password);
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 1, dataProvider = "data_provider_login_excel_hashtable", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessFromDataProviderHashtable(Hashtable<String, String> data) {
        loginPage = new LoginPage();
        loginPage.loginCRM(data.get("Email"), data.get("Password"));
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 2)
    public void loginFailWithEmailInvalid() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin123@example.com", "123456");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test(priority = 3)
    public void loginFailWithPasswordInvalid() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123");
        loginPage.verifyLoginFail("Invalid email or password"); //Invalid email or password
    }

    @Test(priority = 4)
    public void loginFailWithEmailNull() {
        loginPage = new LoginPage();
        loginPage.loginCRM("", "123456");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    @Test(priority = 5)
    public void loginFailWithPasswordNull() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFail("The Password field is required.");
    }

    @Test(priority = 6)
    public void loginFailWithEmailNullAndPasswordNull() {
        loginPage = new LoginPage();
        loginPage.loginCRM("", "");
        loginPage.verifyLoginFailWithNullFields_ArrayList(2);
    }

}
