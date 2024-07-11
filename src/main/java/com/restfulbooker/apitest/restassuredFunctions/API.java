package com.restfulbooker.apitest.restassuredFunctions;

import java.io.File;
import java.util.List;

import com.restfulbooker.apitest.actions.HTTPOperation;
import com.restfulbooker.apitest.actions.ValidatorOperation;
import com.restfulbooker.apitest.interfaces.IApi;
import com.restfulbooker.apitests.utilities.Helper;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;

public class API implements IApi {

	RequestSpecification reqSpec;
	HTTPOperation method;
	String url;
	Response resp;

	@Override
	public void init(String url, HTTPOperation method) {
		// TODO Auto-generated method stub
		this.url = url;
		this.method = method;
		reqSpec = given();
	}

	public void initBase(String baseConst) {
		Helper getHelp = new Helper();
		getHelp.set_path("src/main/resources/Constants.properties");
		try {
			RestAssured.baseURI = getHelp.loadProperties(baseConst);
		} catch (Exception e) {
			e.printStackTrace();
		}
		reqSpec = RestAssured.given();
	}

	@Override
	public void setHeader(String[][] head) {
		// TODO Auto-generated method stub
		for (int row = 0; row < head.length; row++)
			for (int col = 0; col < head[row].length; row++)
				reqSpec.header(head[row][col], head[row][col + 1]);
	}

	@Override
	public void setHeader(String head, String val) {
		// TODO Auto-generated method stub
		reqSpec.header(head, val);
	}

	@Override
	public void setBody(String body) {
		// TODO Auto-generated method stub
		reqSpec.body(body);
	}

	@Override
	public void setFormParam(String key, String val) {
		// TODO Auto-generated method stub
		reqSpec.formParam(key, val);
	}

	@Override
	public void setQueryParam(String key, String val) {
		// TODO Auto-generated method stub
		reqSpec.queryParam(key, val);
	}

	@Override
	public String callIt() {
		// TODO Auto-generated method stub
		if (method.toString().equalsIgnoreCase("get")) {
			resp = reqSpec.get(url);
			return resp.asString();
		} else if (method.toString().equalsIgnoreCase("post")) {
			resp = reqSpec.post(url);
			return resp.asString();
		} else if (method.toString().equalsIgnoreCase("patch")) {
			resp = reqSpec.patch(url);
			return resp.asString();
		} else if (method.toString().equalsIgnoreCase("put")) {
			resp = reqSpec.put(url);
			return resp.asString();
		} else if (method.toString().equalsIgnoreCase("delete")) {
			resp = reqSpec.delete(url);
			return resp.asString();
		}
		return "invalid method set for API";
	}

	@Override
	public API assertIt(String key, Object val, ValidatorOperation operation) {
		// TODO Auto-generated method stub
		switch (operation.toString()) {
		case "EQUALS":
			resp.then().body(key, equalTo(val));
			break;
		case "KEY_PRESENTS":
			resp.then().body(key, hasKey(key));
			break;
		case "HAS_ALL":
			break;
		case "NOT_EQUALS":
			resp.then().body(key, not(equalTo(val)));
			break;
		case "EMPTY":
			resp.then().body(key, empty());
			break;
		case "NOT_EMPTY":
			resp.then().body(key, not(emptyArray()));
			break;
		case "NOT_NULL":
			resp.then().body(key, notNullValue());
			break;
		case "HAS_STRING":
			resp.then().body(key, containsString((String) val));
			break;
		case "SIZE":
			resp.then().body(key, hasSize((int) val));
			break;
		}
		return this;
	}

	@Override
	public void assertIt(List<List<Object>> data) {
		// TODO Auto-generated method stub
		for (List<Object> li : data) {
			switch (((ValidatorOperation) li.get(2)).toString()) {
			case "EQUALS":
				resp.then().body(((String) li.get(0)), equalTo((String) li.get(1)));
				break;
			case "KEY_PRESENTS":
				resp.then().body(((String) li.get(0)), hasKey((String) li.get(1)));
				break;
			case "HAS_ALL":
				break;
			}
		}
	}

	public API assertIt(int code) {
		resp.then().statusCode(code);
		return this;
	}

	@Override
	public void fileUpload(String key, String path, String m) {
		// TODO Auto-generated method stub
        reqSpec.multiPart(key,new File(path),m);
	}

	@Override
	public String extractString(String path) {
		// TODO Auto-generated method stub
		return resp.jsonPath().getString(path);
	}

	@Override
	public int extractInt(String path) {
		// TODO Auto-generated method stub
		return resp.jsonPath().getInt(path);
	}

	@Override
	public List extractList(String path) {
		// TODO Auto-generated method stub
		return resp.jsonPath().getList(path);
	}

	@Override
	public Object extractIt(String path) {
		// TODO Auto-generated method stub
		return resp.jsonPath().get(path);
	}

	@Override
	public String extractHeader(String header_name) {
		// TODO Auto-generated method stub
		return resp.header(header_name);
	}

	@Override
	public String getResponseString() {
		// TODO Auto-generated method stub
		return resp.asString();
	}

	@Override
	public void printResp() {
		// TODO Auto-generated method stub
       resp.print();
	}


}
