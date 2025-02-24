package com.anhtester.handle_UploadFile;

import com.anhtester.common.BaseTest;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class HandleUploadFile extends BaseTest {
    @Test
    public void testUploadFileWithSendKeys() {
        WebUI.openURL("https://the-internet.herokuapp.com/upload");
        WebUI.waitForPageLoaded();
        WebUI.sleep(2);

        By inputFileUpload = By.xpath("//input[@id='file-upload']");

        //String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\image2.jpeg";

        //Dùng File.separator để tự động thay thế dấu phân cách đường dẫn trên hệ điều hành
        String filePath2 = SystemHelper.getCurrentDir() + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testdata" + File.separator + "image2.jpeg";

        WebUI.setText(inputFileUpload, filePath2);

        WebUI.sleep(2);
        WebUI.clickElement(By.xpath("//input[@id='file-submit']"));
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(By.xpath("//h3[normalize-space()='File Uploaded!']")), "Can not upload file.");
    }

    @Test
    public void testUploadFileWithRobotClass() {
        WebUI.openURL("https://files.fm/");
        WebUI.waitForPageLoaded();
        WebUI.sleep(3);

        By textOnPage = By.xpath("//div[@id='file_select_dragndrop_text']");
        By divFileUpload = By.xpath("//div[@id='uploadifive-file_upload']");
        By inputFileUpload = By.xpath("//div[@id='file_select_button']//input[@id='file_upload']");

        String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\image1.jpg";

        WebUI.uploadFileWithRobotClass(divFileUpload, filePath);

        //Verify file đã upload thành công
        By fileNameAfterUploadSuccess = By.xpath("//span[@class='filename']");

        Assert.assertTrue(WebUI.checkElementExist(fileNameAfterUploadSuccess), "Can not upload file.");
        WebUI.assertEquals(WebUI.getElementText(fileNameAfterUploadSuccess), "image1.png", "The file name not match.");
    }
}
