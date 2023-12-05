package com.qa.gorest.base;


import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.configuration.ConfigurationManager;
import com.qa.gorest.restclient.RestClient;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	public static final String GOREST_ENPOINT ="/public/v2/users";
	public static final String GOREST_CREATE_ENDPOINT ="/public/v2/users";
	
	protected Properties prop;
	protected ConfigurationManager configManager;
	protected RestClient restClient;
	protected String baseURI;
	
	@Parameters({"baseURI"})
	@BeforeTest
	public void setUp(String baseURI) {
		RestAssured.filters(new AllureRestAssured());
		
		configManager = new ConfigurationManager();
		prop = configManager.initProp();
//		String baseURI = prop.getProperty("baseURI");
		this.baseURI = baseURI;
		//restClient = new RestClient(prop, baseURI);
		
	}

}
