package com.qa.gorest.restclient;

import java.util.Map;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	
	private RequestSpecBuilder requestSpecBuilder;
	private Properties prop;
	private String baseURI;
	private boolean isAuthHeaderAdded =false;
//	private static final String BASE_URI = "https://gorest.co.in";
//	private static final String BEARER_TOKEN = "Bearer 7267c9d99618120d69624f04ab4e1c8263e4cfbd21ad56ef026d26fcb12aa74f";
//	
//	static {
//		requestSpecBuilder = new RequestSpecBuilder();
//	}
	
	public RestClient(Properties prop,String baseURI) {
		 requestSpecBuilder = new RequestSpecBuilder();
		 this.prop=prop;
		 this.baseURI=baseURI;
	}
	
	public void addAurthorizationHeader() {
		if(!isAuthHeaderAdded) {
		requestSpecBuilder.addHeader("Authorization",prop.getProperty("tokenId") );
		isAuthHeaderAdded= true;
		}
	}
	
	public void setContentType(String contentType) {
		
		if(contentType.toLowerCase()=="json") {
			requestSpecBuilder.setContentType(ContentType.JSON);
		}else if(contentType.toLowerCase()=="xml") {
			requestSpecBuilder.setContentType(ContentType.XML);
		}else if(contentType.toLowerCase()=="text") {
			requestSpecBuilder.setContentType(ContentType.TEXT);
		}else {
			System.out.println("Plz enter the right content....");
		}
	}
	
	private RequestSpecification createRequestSpec(boolean includeAuth) {
		requestSpecBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAurthorizationHeader();};
		return requestSpecBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Map<String , String> headerMap,boolean includeAuth) {
		requestSpecBuilder.setBaseUri(baseURI);
		if(headerMap!=null) {
			requestSpecBuilder.addHeaders(headerMap);
		}
		if(includeAuth) {addAurthorizationHeader();};
		return requestSpecBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Map<String , String> headerMap, Map<String,String> queryParam, boolean includeAuth) {
		requestSpecBuilder.setBaseUri(baseURI);
		if(headerMap!=null) {
			requestSpecBuilder.addHeaders(headerMap);
		}
		if(queryParam!=null) {
			requestSpecBuilder.addQueryParams(queryParam);
		}
		if(includeAuth) {addAurthorizationHeader();};
		return requestSpecBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Object requestBody, String contentType, boolean includeAuth) {
		requestSpecBuilder.setBaseUri(baseURI);
		if(contentType!=null) {
			setContentType(contentType);
		}
		if(requestBody!=null) {
			requestSpecBuilder.setBody(requestBody);
		}
		if(includeAuth) {addAurthorizationHeader();};
		return requestSpecBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Object requestBody, String contentType,Map<String , String> headerMap, boolean  includeAuth ) {
		requestSpecBuilder.setBaseUri(baseURI);
		if(contentType!=null) {
			setContentType(contentType);
		}
		if(requestBody!=null) {
			requestSpecBuilder.setBody(requestBody);
		}if(headerMap!=null) {
		}
		if(includeAuth) {addAurthorizationHeader();};
		return requestSpecBuilder.build();
	}
	
	//http methods
	public Response get(String serviceUrl, boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth)).when().get(serviceUrl);
	}
	
	public Response get(String serviceUrl,Map<String, String> headers,boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(headers, includeAuth)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headers, includeAuth)).when().get(serviceUrl);
	}
	
	public Response get(String serviceUrl,Map<String, String> headers, Map<String, String> queryParams,boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(headers, queryParams,includeAuth)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headers, queryParams, includeAuth)).when().get(serviceUrl);
	}
	
	public Response post(String serviceUrl,String contentType, Object requestBody,boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType, includeAuth )).log().all().when().post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType, includeAuth )).when().post(serviceUrl);
	}
	
	public Response post(String serviceUrl,String contentType, Object requestBody, Map<String, String> headers, boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType, headers,includeAuth )).log().all().when().post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType, headers, includeAuth )).when().post(serviceUrl);
	}
	
	public Response put(String serviceUrl,String contentType, Object requestBody, boolean includeAuth,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType, includeAuth )).log().all().when().put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType, includeAuth )).when().put(serviceUrl);
	}
	
	public Response put(String serviceUrl,String contentType, Object requestBody, Map<String, String> headers,boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType, headers,includeAuth )).log().all().when().put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType, headers,includeAuth )).when().put(serviceUrl);
	}
	
	public Response patch(String serviceUrl,String contentType, Object requestBody, boolean includeAuth,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType,includeAuth )).log().all().when().patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType,includeAuth )).when().patch(serviceUrl);
	}
	
	public Response patch(String serviceUrl,String contentType, Object requestBody, Map<String, String> headers,boolean  includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType, headers, includeAuth )).log().all().when().patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType, headers , includeAuth)).when().patch(serviceUrl);
	}
	
	public Response delete(String serviceUr, boolean includeAuth,boolean log) {
		
		if(log) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all().delete(serviceUr);
		}
		return RestAssured.given(createRequestSpec(includeAuth)).delete(serviceUr);
		
	}

	

}
