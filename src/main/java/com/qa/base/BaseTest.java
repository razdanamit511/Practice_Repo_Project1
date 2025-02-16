package com.qa.base;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.utils.CommonUtils;
import com.qa.utils.ConfigReader;

public class BaseTest {

	public static WebDriver driver;
	public static ConfigReader config;
	public static ExtentReports report;
	public static ExtentSparkReporter reporter;
	public static ExtentTest extentTest;

	@BeforeSuite()
	public void setup() throws IOException {

		config = new ConfigReader();

		if (ConfigReader.GetExecutionType().equalsIgnoreCase("local")) {

			switch (ConfigReader.GetBrowserName()) {

			case "chrome":
				driver = new ChromeDriver();
				System.out.println("Driver got instantiated");

				break;
			case "edge":
				driver = new EdgeDriver();
				System.out.println("Driver got instantiated");

				break;

			default:
				System.out.println("Wrong value in browser name or invalid browser name.");
				break;
			}

		}

		report = new ExtentReports();

		reporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReports/extent_report.html");
		reporter.loadXMLConfig(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml");

		report.attachReporter(reporter);

	}

	@BeforeMethod()
	public void MethodStartup(Method m, ITestContext context) {

		String descp = m.getAnnotation(org.testng.annotations.Test.class).description();

		extentTest = report.createTest(m.getName(), descp);

	}

	@AfterMethod
	public void MethodClosure(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass("Test - Passed !!!!!");

		} else if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.fail("Test - Failed !!!!!");

			CommonUtils.CaptureScreenshot(result.getTestContext().getName() + "_" + result.getMethod().getMethodName());

			extentTest.addScreenCaptureFromPath(CommonUtils
					.CaptureScreenshot(result.getTestContext().getName() + "_" + result.getMethod().getMethodName()));

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.fail("Test - Skipped !!!!!");
		}

	}

	@AfterSuite
	public void teardown() {
		driver.quit();
		report.flush();
	}

}
