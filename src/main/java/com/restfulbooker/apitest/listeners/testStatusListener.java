package com.restfulbooker.apitest.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.ITest;

public class testStatusListener  implements ITestListener{

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	public void onStart(ITestContext iTestContext) {
		System.out.println("Initializing Testcases" +iTestContext.getName());
	}
	
	public void onFinish(ITestContext iTestContext) {
		System.out.println("Finishing Testcases" +iTestContext.getName());
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
	}
	
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("Initializing Test Method" +getTestMethodName(iTestResult) + "start");
	}
	
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("Test Method" +getTestMethodName(iTestResult) + "success");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("Test Method" +getTestMethodName(iTestResult) + "failed");
        Object testClass = iTestResult.getInstance();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed");
	}
	
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("Test Method" +getTestMethodName(iTestResult) + "skipped");
        Object testClass = iTestResult.getInstance();
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but with some success ratio"+getTestMethodName(iTestResult));
	}
}
