package com.anhtester.Bai34_AllureReport.testcases;

import com.anhtester.Bai34_AllureReport.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.dataproviders.DataProviderFactory;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Epic("Regression")
    @Feature("DMS")
    @Story("Login")
    @Owner("Anh Tester")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://anhtester.com/dms/873")
    @Issue("https://jira.com/anhtester/dms/issue")
    @Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    @Test(priority = 1, dataProvider = "data_provider_login_success", dataProviderClass = DataProviderFactory.class)
    public void loginSuccess(String email, String password) {
        loginPage = new LoginPage();
        loginPage.loginCRM(email, password);
        loginPage.verifyLoginSuccess();
    }

    @Epic("Regression")
    @Feature("DMS")
    @Story("Login")
    @Owner("Anh Tester")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1, dataProvider = "data_provider_login_excel", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessFromDataProvider(String email, String password) {
        loginPage = new LoginPage();
        loginPage.loginCRM(email, password);
        loginPage.verifyLoginSuccess();
    }

    @Epic("Regression")
    @Feature("Inventory")
    @Story("Login")
    @Owner("Dung")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 1, dataProvider = "data_provider_login_excel_hashtable", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessFromDataProviderHashtable(Hashtable<String, String> data) {
        loginPage = new LoginPage();
        loginPage.loginCRM(data.get("Email"), data.get("Password"));
        loginPage.verifyLoginSuccess();
    }

    @Feature("Smoke")
    @Owner("Tien")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void loginFailWithEmailInvalid() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin123@example.com", "123456");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Feature("Smoke")
    @Owner("Dung")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 3)
    public void loginFailWithPasswordInvalid() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123");
        loginPage.verifyLoginFail("Invalid email or password"); //Invalid email or password
    }

    @Feature("Smoke")
    @Owner("Tien")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 4)
    public void loginFailWithEmailNull() {
        loginPage = new LoginPage();
        loginPage.loginCRM("", "123456");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    @Feature("Regression")
    @Owner("Linh")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 5)
    public void loginFailWithPasswordNull() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFail("The Password field is required.");
    }

    @Feature("Regression")
    @Owner("Linh")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 6)
    public void loginFailWithEmailNullAndPasswordNull() {
        loginPage = new LoginPage();
        loginPage.loginCRM("", "");
        loginPage.verifyLoginFailWithNullFields_ArrayList(2);
    }

}
