package com.project.frontend;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class Delete {
	private String url;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void request() {
		try{
			URL url = new URL(this.url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	
			connection.setRequestMethod("DELETE");
            connection.getResponseCode();
        
	
			System.out.println("Usuário deletado com sucesso!");
		}
        catch (IOException ex){
            System.out.println("\nAlgo deu errado...");
            System.out.println(ex.getLocalizedMessage());
        }
        finally{}
	}
	
	public Delete(String api, String url){
		this.setUrl(api + url);
		this.request();
		
	}
	
}
