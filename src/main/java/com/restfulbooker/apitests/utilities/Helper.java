package com.restfulbooker.apitests.utilities;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.message.BufferedHeader;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;

import com.mysql.cj.xdevapi.JsonArray;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class Helper {
  String path;
  
  public String loadProperties(String property) {
	  Properties prop = new Properties();
	  InputStream input;
	  
	  try {
		  input = new FileInputStream(path);
		  
		  prop.load(input);
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  return prop.getProperty(property);
  }
  
  public Helper set_path(String path2) {
	  path=path2;
	  return this;
  }
  
  public static String[][] ReadTSV(String file_name){
	  String csvFile = file_name;
	  
	  List<String[]> li_2d = new ArrayList<String[]>();
	  
	  BufferedReader br = null;
	  String line = "";
	  String SplitBy = "\t";
	  
	  try {
		  br=new BufferedReader(new FileReader(csvFile));
		  br.readLine();
		  while((line=br.readLine())!=null) {
			  li_2d.add(line.split(SplitBy));
		  }
	  }catch(FileNotFoundException e) {
		  e.printStackTrace();
	  }catch(Exception e) {
		  e.printStackTrace();
	  }finally {
		  if(br!=null) {
			  try {
				  br.close();
			  }catch(IOException e) {
				  e.printStackTrace();
			  }
		  }
	  }
	  
	  String[][] array_2d = new String[li_2d.size()][];
	  for(int i=0;i<li_2d.size();i++) {
		  array_2d[i] = li_2d.get(i);
	  }
	  return array_2d;
  }
  
  public static List<String[]> ReadCSV(String file) throws IOException{
	  FileReader filereader = new FileReader(file);
	  CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
	  List<String[]> allData = csvReader.readAll();
	   return allData;
  }
  
  public static void assertAll(String input,String output,String strict) {
	  try {
		  JSONAssert.assertEquals(input, output, true);
	  }catch(JSONException e) {
		  e.printStackTrace();
	  }
	  }
	
  public static void assertValue(String input, String output) {
	  assertEquals(input, output);
  }	  

  public static void assertString(String actual, String expected,String error_message) {
	  assertEquals(actual, expected,error_message);
  }	  

  public static void assertInt(String actual, String expected,String error_message) {
	  assertEquals(actual, expected,error_message);
  }
  
  public static void assertValue(String input, String key, String value) throws JSONException {
     JSONObject inputJson = new JSONObject();
	  String Key = inputJson.get(key).toString();
	  assertEquals(Key, value);
  }	
  
	/*
	 * public static String getValue(String input, String key) throws JSONException
	 * { JSONObject inputJson = new JSONObject(null); String value =
	 * inputJson.get(key).toString(); return value; }
	 */
  
  public String getSiteToken() {
	String siteToken = null;
	Helper getHelp = new Helper();
	getHelp.set_path("");
	try {
		siteToken = getHelp.loadProperties("SiteToken");
	}catch(Exception e) {
		e.printStackTrace();
	}
   return siteToken;
  }                                    
}
