package com.project.frontend;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.PrintStream;

public class Put {
    private String url;
    private User user;
    private int responseCode;
    
    public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    
    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public  int getResponseCode(){
		return responseCode;
	}

	public void setResponseCode(int code){
		this.responseCode = code;
	}

    public void request(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(this.user);

            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

    
            PrintStream printStream = new PrintStream(connection.getOutputStream());
            printStream.println(json); 
    
            connection.connect(); 
    
    
            InputStream response = (InputStream) connection.getInputStream();
            this.user = mapper.readValue(response, User.class);
            System.out.print("\n");
            System.out.println("id=" + user.getPk());
		    System.out.println("username=" + user.getUsername());
		    System.out.println("password=" + user.getPassword());
        
            System.out.println("Usuário editado com sucesso!");
        }
        catch (JsonProcessingException pe){}
        catch (IOException ex){
            System.out.println("\nAlgo deu errado...");
            System.out.println(ex.getLocalizedMessage());
        }
        finally{

        }
    }

    public Put(String api, String url, User user){
		this.setUrl(api + url);
        this.setUser(user);
		this.request();
	}
}
