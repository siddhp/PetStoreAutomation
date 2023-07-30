package api.utilities;

import java.util.Date;
import java.text.*;
import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtility {

	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public ExtentSparkReporter htmlReporter;

	public static ExtentReports getExtentReport() {
		if (extent == null) {
			extent = new ExtentReports();
			String reportPath = getReportPath();
			ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
			extent.attachReporter(htmlReporter);
		}
		return extent;
	}

	public static ExtentTest createTest(String testName) {
		ExtentTest test = getExtentReport().createTest(testName);
		 extentTest.set(test);
		return test;
	}

	public static void logTestStep(Status status, String message) {
		extentTest.get().log(status, message);
	}

	public static void logTestStep(Status status, String message, Throwable throwable) {
		extentTest.get().log(status, message);
	}

	public static void flushReports() {
		if (extent != null) {
			extent.flush();
			extent = null;
		}
	}

	private static String getReportPath() {
		// Get the directory path where you want to store the report
		// For example, you can create a "reports" directory in your project
		String reportDirectory = "reports/";

		// Create the directory if it doesn't exist
		File directory = new File(reportDirectory);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		// Generate a unique timestamp to use in the report file name
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String timestamp = sdf.format(new Date());

		// Return the full path for the report file
		return reportDirectory + "ExtentReport_" + timestamp + ".html";
	}
}
