package com.axon.util;

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
}
