package com.anhtester.Bai26_ParallelExecutionPOM.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    public By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    public By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    public By iconProfile = By.xpath("//li[contains(@class,'header-user-profile')]");
    public By menuTasks = By.xpath("//span[normalize-space()='Tasks']");
    public By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    public By menuSales = By.xpath("//span[@class='menu-text' and normalize-space()='Sales']");
    public By menuProposals = By.xpath("//span[normalize-space()='Proposals']");

    public CustomerPage clickMenuCustomer(){
        WebUI.waitForElementVisible(menuCustomers);
        WebUI.clickElement(menuCustomers);

        return new CustomerPage();
    }
}