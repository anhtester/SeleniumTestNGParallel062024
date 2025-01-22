package com.anhtester.Bai34_AllureReport.pages;

import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import static com.anhtester.keywords.WebUI.*;

public class CustomerPage extends BasePage {

    private By headerCustomerPage = By.xpath("//span[normalize-space()='Customers Summary']");
    private By buttonAddNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By buttonImportCustomers = By.xpath("//a[normalize-space()='Import Customers']");
    private By inputSearchCustomer = By.xpath("//div[@id='clients_filter']//input[@type='search']");
    private By inputCompany = By.xpath("//input[@id='company']");
    private By inputVat = By.xpath("//input[@id='vat']");
    private By inputPhoneNumber = By.xpath("//input[@id='phonenumber']");
    private By inputWebsite = By.xpath("//input[@id='website']");
    private By dropdownGroup = By.xpath("//button[@data-id='groups_in[]']");
    private By inputSearchGroup = By.xpath("//button[@data-id='groups_in[]']/following-sibling::div//input");
    private By itemVIP = By.xpath("//button[@data-id='groups_in[]']/following-sibling::div//span[normalize-space()='VIP']");
    private By dropdownLanguage = By.xpath("//button[@data-id='default_language']");
    private By itemVietnamese = By.xpath("//span[normalize-space()='Vietnamese']");
    private By inputAddress = By.xpath("//textarea[@id='address']");
    private By inputCity = By.xpath("//input[@id='city']");
    private By inputState = By.xpath("//input[@id='state']");
    private By inputZipCode = By.xpath("//input[@id='zip']");
    private By dropdownCountry = By.xpath("//button[@data-id='country']");
    private By inputSearchCountry = By.xpath("//button[@data-id='country']/following-sibling::div//input");
    private By itemVietnamCountry = By.xpath("//button[@data-id='country']/following-sibling::div//span[normalize-space()='Vietnam']");
    private By buttonSave = By.xpath("//div[@id='profile-save-section']//button[normalize-space()='Save']");
    private By itemCustomerFirst = By.xpath("//table[@id='clients']/tbody/tr[1]/td[3]/a");

    //4 total in customer list
    private By totalCustomers = By.xpath("//span[normalize-space()='Total Customers']/preceding-sibling::span");

    private By headerCustomerDetailPage = By.xpath("//h4[normalize-space()='Profile']");

    public void verifyNavigateToCustomerPage() {
        Assert.assertTrue(WebUI.checkElementExist(headerCustomerPage), "The customer header page not display.");
        WebUI.assertEquals(WebUI.getElementText(headerCustomerPage), "Customers Summary", "The customer header page not match.");
    }

    public void clickButtonAddNewCustomer() {
        WebUI.clickElement(buttonAddNewCustomer);
    }

    public void submitDataForNewCustomer(String customerName) {
        WebUI.setText(inputCompany, customerName);
        WebUI.setText(inputVat, "10");
        WebUI.setText(inputPhoneNumber, "0123456789");
        WebUI.setText(inputWebsite, "https://anhtester.com");

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", DriverManager.getDriver().findElement(By.xpath("//label[normalize-space()='Groups']")));

        WebUI.clickElement(dropdownGroup);

        WebUI.sleep(1);
        WebUI.setText(inputSearchGroup, "VIP");

        WebUI.sleep(1);
        WebUI.clickElement(itemVIP);
        WebUI.clickElement(dropdownGroup);
        WebUI.clickElement(dropdownLanguage);
        WebUI.clickElement(itemVietnamese);
        WebUI.setText(inputAddress, "Ha Noi");
        WebUI.setText(inputCity, "Ha Noi");
        WebUI.setText(inputState, "Hoan Kiem");
        WebUI.setText(inputZipCode, "123456");
        WebUI.clickElement(dropdownCountry);
        WebUI.setText(inputSearchCountry, "Vietnam");
        WebUI.clickElement(itemVietnamCountry);
        WebUI.clickElement(buttonSave);
    }

    public void verifyNavigateToCustomerDetailPage() {
        Assert.assertTrue(WebUI.checkElementExist(headerCustomerDetailPage), "The customer detail header page not display.");
        WebUI.assertEquals(WebUI.getElementText(headerCustomerDetailPage), "Profile", "The customer detail header page not match.");
    }

    public void verifyAddNewCustomerSuccess(String customerName) {
        //Verify alert message

        //Verify data in customer detail
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputCompany).getAttribute("value"), customerName, "The Company name not match.");
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputVat).getAttribute("value"), "10", "The VAT value not match.");
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputPhoneNumber).getAttribute("value"), "0123456789", "The Phone number value not match.");
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputWebsite).getAttribute("value"), "https://anhtester.com", "The Website value not match.");
        WebUI.assertEquals(DriverManager.getDriver().findElement(dropdownGroup).getAttribute("title"), "VIP", "The Group value not match.");
        WebUI.assertEquals(DriverManager.getDriver().findElement(dropdownLanguage).getAttribute("title"), "Vietnamese", "The Language value not match.");
    }

    public void searchAndCheckCustomerInTable(String customerName) {
        clickMenuCustomer();
        WebUI.setText(inputSearchCustomer, customerName);
        WebUI.sleep(2);
        String customerNameInTable = WebUI.getElementText(itemCustomerFirst);
        System.out.println(customerNameInTable);
        WebUI.assertEquals(customerNameInTable, customerName, "The customer name in table not match.");
    }

    public void searchDataCustomer(String data){
        waitForPageLoaded();
        setText(inputSearchCustomer, data);
    }

    public void searchAndCheckDataInTable(int column, String data, String columnName) {
        waitForPageLoaded();
        setTextAndKey(inputSearchCustomer, data, Keys.ENTER);
        sleep(2);
        waitForPageLoaded();
        WebUI.checkDataInTableByColumn_Contains(column, data, columnName);
    }

    public void clickFirstCustomer() {
        WebUI.clickElement(itemCustomerFirst);
    }

    public int getCustomerTotal() {
        String totalString = WebUI.getElementText(totalCustomers);
        System.out.println("getCustomerTotal: " + totalString);
        return Integer.parseInt(totalString);
    }

}
