package com.cura.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cura.pages.HomePage;
import com.cura.pages.LoginPage;
import com.cura.testbase.TestBase;
import com.cura.util.TestUtil;

public class LoginTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	
	ExtentReports extent;
	ExtentTest logger;
	
	public LoginTest() {
		super();
	}
	
	@BeforeTest
	public void set() {
		ExtentHtmlReporter reporter=new ExtentHtmlReporter("/Users/paragneelam/eclipse-workspace/CURAHealthcare/test-output/learn_automation2.html");
	    extent = new ExtentReports();
	    extent.attachReporter(reporter);
	    extent.setSystemInfo("Hostname", "CURA Healthcare");
	    extent.setSystemInfo("Browser Name", prop.getProperty("browser"));
	    extent.setSystemInfo("OS", "Mac");
	}
	
	@BeforeMethod
	public void setUp() {
		initialize();
		loginPage = new LoginPage();
	}
	
	@DataProvider
	public Object[][] getTestData() {
		Object data[][] = TestUtil.getTestData("Sheet 1");
		return data;
	}
	
	@Test(enabled=true, dataProvider = "getTestData")
	public void performLoginTest(String username, String password) {
		logger=extent.createTest("LoginTest");
		homePage = loginPage.login(username, password);
		logger.log(Status.PASS, MarkupHelper.createLabel("Login Passed", ExtentColor.GREEN));
	}
	
	@Test
	public void failedTest() {
		logger=extent.createTest("FailedTest");
		Assert.assertTrue(false);
		logger.log(Status.PASS, MarkupHelper.createLabel("Dummy Failed Test", ExtentColor.GREEN));
	}
	
	@AfterMethod
	public void endtest(ITestResult result) throws IOException{
		
		if (result.getStatus() == ITestResult.FAILURE) {	
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			
			String path = TestUtil.getScreenShot(driver);
			logger.log(Status.FAIL, "Screenshot below: ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		
		driver.close();
		extent.flush();
	}
	@AfterTest
	public void tearDown()  {
		driver.quit();
	}
	
}
