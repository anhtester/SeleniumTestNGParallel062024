package com.anhtester.Bai29_DataProvider.testcases;

import com.anhtester.Bai29_DataProvider.pages.DashboardPage;
import com.anhtester.Bai29_DataProvider.pages.LoginPage;
import com.anhtester.common.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test(priority = 1)
    public void testCheckDashboardTotal(){
        loginPage = new LoginPage();

        //loginPage.loginCRM();

        //dashboardPage = new DashboardPage(drivers);

        dashboardPage = loginPage.loginCRM();
        dashboardPage.verifyInvoicesAwaitingPaymentTotal("0 / 2");
    }

}