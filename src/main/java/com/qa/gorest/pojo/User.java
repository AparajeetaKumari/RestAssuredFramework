package com.qa.gorest.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class User {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("status")
	private String status;

	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public User( String name,String gender, String email,String status) {
		this.name=name;
		this.gender = gender;
		this.email=email;
		this.status = status;
	}
	
//	public User(String id, String name,String gender, String email,String status) {
//		this.id=id;
//		this.name=name;
//		this.gender = gender;
//		this.email=email;
//		this.status = status;
//	}
//	
	

}
