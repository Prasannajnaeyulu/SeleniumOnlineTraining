package com.google.gmail.common;

import com.google.gmail.common.listerners.TestListener;

public class Validate {
	
	public static boolean validateStringEquals(String expected, String actual, boolean ignorecase){
		if(ignorecase){
			if(expected.equalsIgnoreCase(actual)){
				System.out.println("Expected: "+ expected+" and Actual: "+actual+" strings are equal");
				return true;
			}
			else{
				System.out.println("Expected: "+ expected+" and Actual: "+actual+" strings are not equal");
				TestListener.validationfailures++;
				return false;
			}
		}
		else{
			if(expected.equals(actual)){
				System.out.println("Expected: "+ expected+" and Actual: "+actual+" strings are equal");
				return true;
			}
			else{
				System.out.println("Expected: "+ expected+" and Actual: "+actual+" strings are not equal");
				TestListener.validationfailures++;
				return false;
			}
		}		
	}
}
