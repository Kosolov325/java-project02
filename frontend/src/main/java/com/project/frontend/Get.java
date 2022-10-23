package com.project.frontend;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Get {
	private String url;
	private Boolean list;
	private int responseCode;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getList() {
		return list;
	}
	public void setList(Boolean list) {
		this.list = list;
	}

	public  int getResponseCode(){
		return responseCode;
	}

	public void setResponseCode(int code){
		this.responseCode = code;
	}
	
	public void request() {
		try{
			URL url = new URL(this.url);
	
		
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
	
	
			InputStream responseStream = connection.getInputStream();
		
			this.setResponseCode(connection.getResponseCode());
		
			ObjectMapper mapper = new ObjectMapper();
	

			if (this.list) {
				User[] users;
				users = mapper.readValue(responseStream, User[].class);
				System.out.println("[id][username][password]");
				Arrays.asList(users).forEach(user -> System.out.println("[" + user.getPk() + "]" + "[" + user.getUsername() + "]" + "[" + user.getPassword() + "]"));		
			}
			else {
				User user = mapper.readValue(responseStream, User.class);
				System.out.println("id=" + user.getPk());
				System.out.println("username=" + user.getUsername());
				System.out.println("password=" + user.getPassword());
			}
		}
		catch (JsonProcessingException pe){}
        catch (IOException ex){
            System.out.println("\nAlgo deu errado...");
        }
        finally{}
	}
	
	public Get(String api, String url, Boolean list){
		this.setUrl(api + url);
		this.setList(list);
		this.request();
		
	}
	
}
