package api.utilities;



import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.aventstack.extentreports.Status;

public class ExtentReportManager implements ITestListener {

    public void onStart(ITestContext context) {
        ExtentReportUtility.getExtentReport(); // Initialize the ExtentReports instance
    }

    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentReportUtility.createTest(testName); // Create a new ExtentTest for each test method
    }

    public void onTestSuccess(ITestResult result) {
        ExtentReportUtility.logTestStep(Status.PASS, "Test passed!");
    }

    public void onTestFailure(ITestResult result) {
        ExtentReportUtility.logTestStep(Status.FAIL, "Test failed!", result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        ExtentReportUtility.logTestStep(Status.SKIP, "Test skipped!");
    }

    public void onFinish(ITestContext context) {
        ExtentReportUtility.flushReports(); // Flush and close the ExtentReports instance
    }
}