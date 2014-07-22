package com.example.robolectricsample.component;

public class MakeCallRule {
	
	public static boolean checkDialNumberAvailable(String dialNum){
		
		//判斷有沒有+
		if(!dialNum.startsWith("+")){
			return false;
		}
		//trunk
		if(dialNum.startsWith("+8860")){
			return false;
		}
		//長度
		if(dialNum.length()>13 || dialNum.length()<11 ){
			return false;
		}
		return true;
		
	}
}
