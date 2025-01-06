package com.anhtester.Bai28_Excel_File_Data.testcases;

import com.anhtester.Bai28_Excel_File_Data.pages.CustomerPage;
import com.anhtester.Bai28_Excel_File_Data.pages.DashboardPage;
import com.anhtester.Bai28_Excel_File_Data.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.ExcelHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;

    @Test
    public void testAddNewCustomer() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/CRM.xlsx", "Customer");

        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        customerPage = dashboardPage.clickMenuCustomer();

        customerPage.verifyNavigateToCustomerPage();
        int beforeTotal = customerPage.getCustomerTotal();
        customerPage.clickButtonAddNewCustomer();
        customerPage.submitDataForNewCustomer(1);
        customerPage.verifyNavigateToCustomerDetailPage();
        customerPage.verifyAddNewCustomerSuccess(1);

        customerPage.clickMenuCustomer();
        int afterTotal = customerPage.getCustomerTotal();
        Assert.assertEquals(afterTotal, beforeTotal + 1, "The total customer before and after add new not match.");
        excelHelper.setCellData("Passed", "Status", 1);
    }

    @Test
    public void testAddNewCustomer_SearchInTable() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        customerPage = dashboardPage.clickMenuCustomer();

        customerPage.verifyNavigateToCustomerPage();
        int beforeTotal = customerPage.getCustomerTotal();
        customerPage.clickButtonAddNewCustomer();
        customerPage.submitDataForNewCustomer(1);

        //Search and check customer name in table
        customerPage.searchAndCheckCustomerInTable(1);
        customerPage.clickFirstCustomer();

        //Verify data of new customer in profile page
        customerPage.verifyNavigateToCustomerDetailPage();
        customerPage.verifyAddNewCustomerSuccess(1);

        //Compare total customer
        customerPage.clickMenuCustomer();
        int afterTotal = customerPage.getCustomerTotal();
        Assert.assertEquals(afterTotal, beforeTotal + 1, "The total customer before and after add new not match.");
    }

}
