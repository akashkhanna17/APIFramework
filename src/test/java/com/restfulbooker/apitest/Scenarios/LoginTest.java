package com.restfulbooker.apitest.Scenarios;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.restfulbooker.apitest.baseAPI.Auth;
import com.restfulbooker.apitest.listeners.ExtentTestManager;

public class LoginTest {

	@Test
	public void validLoginTest(Method m) {
		ExtentTestManager.startTest(m.getName());
		Auth resp = new Auth();
		resp.getLoginToken("admin", "password123");
	}
	
}
