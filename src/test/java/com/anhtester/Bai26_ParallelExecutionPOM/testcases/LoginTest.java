package com.anhtester.Bai26_ParallelExecutionPOM.testcases;

import com.anhtester.Bai26_ParallelExecutionPOM.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.PropertiesHelper;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test(priority = 1)
    public void loginSuccess(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        loginPage.verifyLoginSuccess();
        //Chỗ này hàm loadAllFiles() đã được gọi trước bên BaseTest
        PropertiesHelper.setValue("EMAIL", "admin@example.com", "src/test/resources/configs/data.properties");
    }

    @Test(priority = 2)
    public void loginFailWithEmailInvalid(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin123@example.com", "123456");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test(priority = 3)
    public void loginFailWithPasswordInvalid(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123");
        loginPage.verifyLoginFail("Invalid email or password"); //Invalid email or password
    }

    @Test(priority = 4)
    public void loginFailWithEmailNull(){
        loginPage = new LoginPage();
        loginPage.loginCRM("", "123456");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    @Test(priority = 5)
    public void loginFailWithPasswordNull(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFail("The Password field is required.");
    }

    @Test(priority = 6)
    public void loginFailWithEmailNullAndPasswordNull(){
        loginPage = new LoginPage();
        loginPage.loginCRM("", "");
        //loginPage.verifyLoginFailWithNullFields();
        loginPage.verifyLoginFailWithNullFields_ArrayList(2);
    }

}
