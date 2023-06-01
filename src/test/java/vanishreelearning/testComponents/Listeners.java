package vanishreelearning.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import vanishreelearning.resources.ExtentreportNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent=ExtentreportNG.getreportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>(); //Thread safe
	@Override
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//set unique Thread ID
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "test passed");
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
		e1.printStackTrace();
		}
		String filepath=null;
		try {
			 filepath=getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		
	}

}
