package com.anhtester.listeners;

import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static int test_total;
    private static int test_passed_total;
    private static int test_failed_total;
    private static int test_skipped_total;

    @Override
    public void onStart(ITestContext result) {
        System.out.println("Setup môi trường: " + result.getStartDate());
        //CaptureHelper.startRecord("VideoSuite01");
        //Load file Properties config
        PropertiesHelper.loadAllFiles();

        //CÓ thể kết nối Database trước để lấy data test
        //Call API bên thứ 3 để xác thực 1 cái gì đó khi cần
    }

    @Override
    public void onFinish(ITestContext result) {
        System.out.println("Kết thúc bộ test: " + result.getEndDate());
        System.out.println("Test Total: " + test_total);
        System.out.println("Test Passed Total: " + test_passed_total);
        System.out.println("Test Failed Total: " + test_failed_total);
        System.out.println("Test Skipped Total: " + test_skipped_total);

        //CaptureHelper.stopRecord();

        //Gửi mail (đính kèm file logs, file report)
        //Xuất report
    }

    @Override
    public void onTestStart(ITestResult result) {
        //Ghi vào logs File
        //Ghi vào report chi tiết từng bước
        System.out.println("Bắt đầu chạy test case: " + result.getName());
        test_total++;
        CaptureHelper.startRecord(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Đây là test case chạy thành công: " + result.getName());
        System.out.println("Status: " + result.getStatus());
        test_passed_total++;
        CaptureHelper.stopRecord();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Đây là test case bị fail: " + result.getName());
        System.out.println("Status: " + result.getStatus());
        test_failed_total++;
        CaptureHelper.captureScreenshot(result.getName());
        CaptureHelper.stopRecord();

        //Tạo ticket Jira
        //Gửi hình chụp và logs lên Slack/Telegram/MSTeam
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Đây là test case bị bỏ qua: " + result.getName());
        System.out.println("Status: " + result.getStatus());
        test_skipped_total++;
        CaptureHelper.stopRecord();

        //Tạo ticket Jira
        //Gửi hình chụp và logs lên Slack/Telegram/MSTeam
    }

}