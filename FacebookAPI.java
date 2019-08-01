package com.facebook.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class FacebookAPI {
	private static String CLIENT_ID = "693810394374991";
	private static String CLIENT_SECRET = "3445b77cba536a5de4ea6ca00ba8c138";
	
	
	public static void main(String [] args) {
		FacebookAPI api = new FacebookAPI();
		
		api.getPosts();
	}

	private String getAccessToken() {
		URL url;
		InputStream is = null;
		try {
			url = new URL("https://graph.facebook.com/oauth/access_token"
					+ "?client_id=" + CLIENT_ID
					+ "&client_secret=" + CLIENT_SECRET
					+ "&grant_type=client_credentials");
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			is = request.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuffer buffer = new StringBuffer();
		Scanner scan = new Scanner(is, "UTF-8");
		while (scan.hasNextLine()) {
			buffer.append(scan.nextLine());
		}
		
		while (scan.hasNextLine()) {
			buffer.append(scan.nextLine());
		}
		
		JSONObject obj = new JSONObject(buffer.toString());
		String access_token = obj.getString("access_token");
		return access_token;
	}

	public void getPosts() {
		URL url;
		InputStream is = null;
		String access_token = getAccessToken();
		System.out.println("access_token : " + access_token);
		try {
			url = new URL("https://graph.facebook.com/v4.0/100245254659946/feed"
					+ "?access_token=" + access_token
					+ "&locale=ko_KR"
					+ "&limit=300");
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			is = request.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuffer buffer = new StringBuffer();
		Scanner scan = new Scanner(is, "UTF-8");

		while (scan.hasNextLine()) {
			buffer.append(scan.nextLine());
		}
		
		JSONObject obj = new JSONObject(buffer.toString());
		JSONArray jsonArray = obj.getJSONArray("data");
		
		for(Object jsonObj :jsonArray ) {
			JSONObject object = (JSONObject) jsonObj;
			
			System.out.println(object.getString("message"));
		}

	}


}
