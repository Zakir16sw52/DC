package com.geeks.util;

import java.util.Random;

public class Encryption {
	public static String encrypt(int number) {
		Random rand=new Random();
		String encryptedText="";
		char text=' ';
		for(int i=1;i<=6;i++) {
			int value=rand.nextInt(100);
			text=(char)value;
			System.out.println("value="+value);
			if(Character.isDigit(text)||Character.isAlphabetic(text)) {
				encryptedText+=text;
			}
			else {
				i--;
			}
		}
		  encryptedText+=String.valueOf(number);
		 
		  for(int i=1;i<=6;i++) {
				int value=rand.nextInt(100);
				text=(char)value;
				System.out.println("value="+value);
				if(Character.isDigit(text)||Character.isAlphabetic(text)) {
					encryptedText+=text;
				}
				else {
					i--;
				}
			}
		return encryptedText;
	}

	
	public static int decrypt(String text) {
		String value="";
		for(int i=6;i<text.length();i++) {
			value+=text.charAt(i);
			if(value.length()+12==text.length()) {
				break;
			}
		}
		 return Integer.parseInt(value);
	}
	
}
