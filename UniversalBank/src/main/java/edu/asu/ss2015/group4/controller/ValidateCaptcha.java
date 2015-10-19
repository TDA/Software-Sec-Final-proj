package edu.asu.ss2015.group4.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;

public class ValidateCaptcha {

	 private final static String USER_AGENT = "Mozilla/5.0";
	 public static boolean validateCaptchaResponse (String gRecaptchaResponse) throws IOException {
		 
		   	
		 	 
		
		 	URL obj = new URL("https://www.google.com/recaptcha/api/siteverify");
	        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
	 
	        // add reuqest header
	        con.setRequestMethod("POST");
	        con.setRequestProperty("User-Agent", USER_AGENT);
	        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	 
	        String postParams = "secret=" + "6LeEgw4TAAAAAJOrbfTdmEDIs7vstZyzHfpIJuG8" + "&response="
	                + gRecaptchaResponse;
	 
	        System.out.println("postParams" + postParams);
	        // Send post request
	        con.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        wr.writeBytes(postParams);
	        wr.flush();
	        wr.close();
	 
	        int responseCode = con.getResponseCode();
	 
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	 
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
		
	        JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
	        JsonObject jsonObject = jsonReader.readObject();
	        jsonReader.close();
	        
	        System.out.println("going to return :" + jsonObject.getBoolean("success"));
	        return jsonObject.getBoolean("success");
		 
	 }
	
}
