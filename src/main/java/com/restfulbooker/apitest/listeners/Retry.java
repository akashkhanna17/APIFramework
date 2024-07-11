package com.restfulbooker.apitest.listeners;

import org.testng.ITestResult;

public class Retry {
   
	private int count = 1;
	private static int maxTry = 1;
	
	public boolean retry(ITestResult iTestResult) {
		if(!iTestResult.isSuccess()) {
			if(count<maxTry) {
				count++;
				iTestResult.setStatus(ITestResult.FAILURE);
				return true;
			}
		}
		else {
			iTestResult.setStatus(iTestResult.SUCCESS);
		}
		return false;
	}
	
}
