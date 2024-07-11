package com.restfulbooker.apitest.baseAPI;

import com.restfulbooker.apitest.actions.HTTPOperation;
import com.restfulbooker.apitest.restassuredFunctions.API;

public class Auth extends API{

	public Auth() {
	
}
	
	/**
	 * Creates a new auth token to use for access
	 * @param username
	 * @param password
	 */
	private void createToken(String username, String password) {
		initBase("Host");
        init("/auth",HTTPOperation.POST);
        setHeader("Content-Type","application/json");
        setBody("{ \"username\" : \""+username+"\",\"password\" : \""+password+"\"}");
	}
	
	/**
	 * @param userName: Username String value for the application
	 * @param Password: Password String value for the application
	 * @return  login token
	 */
	public String getLoginToken(String userName, String Password) {
		createToken(userName, Password);
		String response = callIt();
		return response;
	}
	
}
