package com.project.frontend;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.PrintStream;

public class Post {
    private String url;
    private User user;

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

    public void request(){
        try{
         ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(this.user);

            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

    
            PrintStream printStream = new PrintStream(connection.getOutputStream());
            printStream.println(json); 
    
            connection.connect(); 
            
            InputStream response = (InputStream) connection.getInputStream();
            this.user = mapper.readValue(response, User.class);
            System.out.println("id=" + user.getPk());
		    System.out.println("username=" + user.getUsername());
		    System.out.println("password=" + user.getPassword());
        
            System.out.println("Usuário cadastrado com sucesso!");
        
        }
        catch (JsonProcessingException pe){}
        catch (IOException ex){
            System.out.println("\nAlgo deu errado...");
            System.out.println(ex.getLocalizedMessage());
        }
        finally{}

        
    }

    public Post(String api, String url, User user){
		this.setUrl(api + url);
        this.setUser(user);
		this.request();
	}
}
