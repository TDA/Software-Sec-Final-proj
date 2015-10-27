// Referenced from mkyong
package edu.asu.ss2015.group4.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

public class OTPGenerator {
	public int generateOTP(){
		Random rand = new Random();
		int randomNum = rand.nextInt(737568)+256846;
		String IV = Integer.toString(randomNum);
	 	String app1Hash;
		String app1Password;
	 	app1Hash = genHash(IV);
		app1Password = genPassword(app1Hash); 	
		HashMap <String,Integer> h = new HashMap();
		app1Hash = genHash(app1Hash); //send old hash as seed for next sha hash
		app1Password = genPassword(app1Hash);
		while(h.containsKey(app1Password)){
			h.put(app1Password, 0);
			app1Hash = genHash(app1Hash); //send old hash as seed for next sha hash
			app1Password = genPassword(app1Hash);
		}
		
		return Integer.parseInt(app1Password);
	}
	
	private String genHash(String input){
   		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		md.update(input.getBytes());
   		byte byteData[] = md.digest();
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < byteData.length; i++) {
     		sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    	}
    	return sb.toString();
	}

	private String genPassword(String hash) { 
		char[] hashArray = hash.toCharArray();
		char[] shortHashArray;
		shortHashArray = new char[12];
		char[] tempChar = new char[6];
		for(int i = 0; i < 12; i++) {
			shortHashArray[i] = hashArray[52+i]; 
		}
		String Password = "";
		int hexNum;
		for (int i = 0; i < 6; i++) {
			tempChar[0] = shortHashArray[2*i];
			tempChar[1] = shortHashArray[2*i+1];
			String temp = new String(tempChar); 
			temp = temp.trim();
			hexNum = Integer.parseInt(temp, 16);
			String hexNumString = Integer.toString(hexNum);
			
			if (Password.length() == 5) { 
				Password += hexNumString.charAt(0);
			}
			else if (Password.length() == 4) {              
				if(hexNum<10) {  
					Password += hexNumString.charAt(0);
				}
				else {			 
					Password += hexNumString.charAt(0);
					Password += hexNumString.charAt(1);
				}
			}
			else if (Password.length() >= 6) { 
				break;
			}
			else {
				Password += hexNumString; 
			}
		}

		return Password;
	}

}
