package com.axon.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;

public class JSPHelper {
	
	public static String pickString(long i){
		if(i < 0){
			return "";
		}
		return "value=\""+ i + "\"";
	}

	public static String pickString(String s){
		if(s == null){
			return "";
		}
		return "value=\""+ s + "\"";
	}
	
	public static String pickString(Timestamp d){
		if(d == null){
			return "";
		}
		return "value=\""+ d.toString().substring(0,10) + "\"";
	}
	
	public static String pickColor(){
		String s = "";
		//for(int i = 0; i < 2; i++){
			int hex = 0xC0;
			s += (Integer.toHexString((int) (hex + Math.random()*0x40))); 
		//}
		s += "EEFF";
		System.out.println(s);
		return s;
	}
	
	public static String pickColor(String str){
		String s = "";
		for(int i = 0; i < 3; i++){
			int hex = 0xA0;
			s += (Integer.toHexString((int) (hex + str.hashCode()%0x60))); 
		}
		System.out.println(s);
		return s;
	}
	
	public static String display(File f){
		String name = "File: " + f.getName();
		String link = "<form method=\"post\" action=\"document\"><input id=\"file\" name=\"file\" type=\"hidden\" value=\"" + f.getAbsolutePath() + "\"> <input type=\"submit\" value=\"File Source\"></form>";
		return name + link;
	}
}
