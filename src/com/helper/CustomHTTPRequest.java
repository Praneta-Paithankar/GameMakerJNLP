package com.helper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class CustomHTTPRequest {


	    private static HttpURLConnection connection;

	    public void postRequest(String playerName, String gameName, int score) throws IOException
	    {

	        String url = "http://192.168.43.223:8080/saveScore";

	        try {

	            URL myurl = new URL(url);
	            connection = (HttpURLConnection) myurl.openConnection();
//
//	            connection.setDoOutput(true);
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("User-Agent", "Java client");
//	            connection.setRequestProperty("Content-Type", "application/json");

	            JSONObject userData = new JSONObject();
	            
	            userData.put("userName", playerName);
	            userData.put("game", gameName);
	            userData.put("score", score);
	            
	            StringEntity entity = new StringEntity(userData.toString(), ContentType.APPLICATION_JSON);

	            HttpClient httpClient = HttpClientBuilder.create().build();
	            HttpPost request = new HttpPost(url);
	            request.setEntity(entity);

	            HttpResponse response = httpClient.execute(request);
	            System.out.println(response.getStatusLine().getStatusCode());

	        }finally {
	        	if(connection != null)
	        		connection.disconnect();
			}
	    }	
}
