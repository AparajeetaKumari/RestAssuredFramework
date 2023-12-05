package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.restclient.RestClient;
import com.qa.gorest.utils.JsonPathValidator;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;


public class GetUsersTest extends BaseTest{
	
	@BeforeMethod
	public void getUsersSetup() {
	    restClient = new RestClient(prop, baseURI);
	}

	
	@Test
	public void getAllUsers() {	
	 Response res = restClient.get(GOREST_ENPOINT,true, true);
	 Assert.assertEquals(res.getStatusCode(), APIHttpStatus.OK_200.getCode());
	 
	 JsonPathValidator js = new JsonPathValidator();
	 List<Integer> ids= js.readList(res, "$.[?id]");
	 for (Integer integer : ids) {
		System.out.println(integer);
	}
	}
	
	@Test
	public void getSpecificUser() {
	restClient.get(GOREST_ENPOINT+"/5805285", true,true).then().log().all().assertThat().statusCode(APIHttpStatus.OK_200.getCode()).and().body("id", equalTo(5805285));
		
		
	}
	
	@Test
	public void getUserWithQueryParam() {
		Map<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("email", "api1700915556403@api.com");
		queryParam.put("status", "active");
		restClient.get(GOREST_ENPOINT+"/",null,queryParam,true, true).then().log().all().assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		
			}


	
}
