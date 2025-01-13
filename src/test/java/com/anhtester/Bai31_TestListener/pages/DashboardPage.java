package com.anhtester.Bai31_TestListener.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DashboardPage extends BasePage {

    private By totalInvoicesAwaitingPayment = By.xpath("(//span[normalize-space()='Invoices Awaiting Payment']/parent::div)/following-sibling::span");
    private By totalConvertedLeads = By.xpath("(//span[normalize-space()='Converted Leads']/parent::div)/following-sibling::span");

    public void verifyInvoicesAwaitingPaymentTotal(String total) {
        Assert.assertTrue(WebUI.isElementDisplayed(totalInvoicesAwaitingPayment), "The Invoices Awaiting Payment total label not display.");
        WebUI.assertEquals(WebUI.getElementText(totalInvoicesAwaitingPayment), total, "The Invoices Awaiting Payment total not match.");
    }

}