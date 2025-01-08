package com.anhtester.Bai29_DataProvider.testcases;

import com.anhtester.Bai29_DataProvider.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.dataproviders.DataProviderFactory;
import com.anhtester.helpers.PropertiesHelper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test(priority = 1, dataProvider = "data_provider_login_success", dataProviderClass = DataProviderFactory.class)
    public void loginSuccess(String email, String password) {
        loginPage = new LoginPage();
        loginPage.loginCRM(email, password);
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 1, dataProvider = "data_provider_login_excel", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessFromDataProvider(String email, String password) {
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
