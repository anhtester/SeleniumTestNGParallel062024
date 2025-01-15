package com.anhtester.listeners;

import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.utils.LogUtils;
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
        LogUtils.info("⚙\uFE0F Setup môi trường onStart: " + result.getStartDate());
        //CaptureHelper.startRecord("VideoSuite01");
        //Load file Properties config
        PropertiesHelper.loadAllFiles();

        //CÓ thể kết nối Database trước để lấy data test
        //Call API bên thứ 3 để xác thực 1 cái gì đó khi cần
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("Kết thúc bộ test: " + result.getEndDate());
        LogUtils.info("⭐\uFE0F Test Total: " + test_total);
        LogUtils.info("⭐\uFE0F Test Passed Total: " + test_passed_total);
        LogUtils.info("⭐\uFE0F Test Failed Total: " + test_failed_total);
        LogUtils.info("⭐\uFE0F Test Skipped Total: " + test_skipped_total);

        //CaptureHelper.stopRecord();

        //Gửi mail (đính kèm file logs, file report)
        //Xuất report
    }

    @Override
    public void onTestStart(ITestResult result) {
        //Ghi vào logs File
        //Ghi vào report chi tiết từng bước
        LogUtils.info("Bắt đầu chạy test case: " + result.getName());
        test_total++;
        CaptureHelper.startRecord(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("✅ Test case " + result.getName() + " is passed.");
        test_passed_total++;
        CaptureHelper.stopRecord();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("❌ Test case " + result.getName() + " is failed.");
        LogUtils.error(result.getThrowable());
        test_failed_total++;
        CaptureHelper.captureScreenshot(result.getName());
        CaptureHelper.stopRecord();

        //Tạo ticket Jira
        //Gửi hình chụp và logs lên Slack/Telegram/MSTeam
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("\uD83D\uDD25 Test case " + result.getName() + " is skipped.");
        LogUtils.warn(result.getThrowable());
        test_skipped_total++;
        CaptureHelper.stopRecord();

        //Tạo ticket Jira
        //Gửi hình chụp và logs lên Slack/Telegram/MSTeam
    }

}