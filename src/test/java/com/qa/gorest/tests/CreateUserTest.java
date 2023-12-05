package com.qa.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.restclient.RestClient;
import com.qa.gorest.utils.StringUtils;

import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {
	
	@BeforeMethod
	public void getUsersSetup() {
	    restClient = new RestClient(prop, baseURI);
	    System.out.println("base uri is::"+baseURI);
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return new Object[][]{
			{"Aparajeeta","female","active"},
			{"Aiden","male","active"},
			{"Benhur","male","active"}
		};
	}
	
	@Test(dataProvider = "getTestData")
	public void createUserTest(String name,String gender, String status) {
		User user = new User(name,gender,StringUtils.getRandomEmailId(),status);
		Response response =restClient.post(GOREST_CREATE_ENDPOINT, "json", user, true, true);
		Assert.assertEquals(response.getStatusCode(), APIHttpStatus.CREATED_201.getCode());
		System.out.println("Response id is::"+response.getBody().asString());
		String id = response.jsonPath().getString("id");		
		
		System.out.println("Id is ::"+id);
		
		//Get resource
		RestClient resctClientForGet = new RestClient(prop, baseURI);
		Response res = resctClientForGet.get(id,false, true);	
		Assert.assertEquals(response.getStatusCode(), APIHttpStatus.CREATED_201 .getCode());
		
	}

}
