package com.restfulbooker.apitest.interfaces;

import com.restfulbooker.apitest.actions.HTTPOperation;
import com.restfulbooker.apitest.actions.ValidatorOperation;
import java.util.List;

public interface IApi {

	public void init(String url, HTTPOperation method);

	public void setHeader(String[][] head);

	public void setHeader(String head, String val);

	public void setBody(String body);

	public void setFormParam(String key, String val);

	public void setQueryParam(String key, String val);

	public String callIt();

	public Object assertIt(int code);

	public Object assertIt(String key, Object val, ValidatorOperation operation);

	public void assertIt(List<List<Object>> data);

	public static void failTest(String expected, String present) {
		throw new AssertionError("Does not contain the expected " + expected + "\n but had" + present);
	}

	public static void failTest(String message) {
		throw new AssertionError(message);
	}

	public void fileUpload(String key, String path, String m);

	public String extractString(String path);

	public int extractInt(String path);

	public List extractList(String path);

	public Object extractIt(String path);

	public String extractHeader(String header_name);

	public String getResponseString();

	public void printResp();

}
