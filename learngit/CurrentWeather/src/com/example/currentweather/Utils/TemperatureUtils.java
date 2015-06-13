package com.example.currentweather.Utils;
/**
 * @项目名:   CurrentWeather
 * @包名:    com.example.currentweather.Utils
 * @类名:    TemperatureUtils
 * @创建着:   李宁
 * @创建时间:  2015-5-15下午4:31:37
 * @描述:    TODO
 */
public class TemperatureUtils
{
public static int[] getTemperature(String temprature){
	int [] tempratures=new int [2];
	int changeTemp = temprature.indexOf("~");
	if(changeTemp==-1){
		return tempratures;
	}
	String temp1 = temprature.substring(0, changeTemp - 1).trim();
	String temp2 = temprature.substring(changeTemp + 1,temprature.length()-1).trim();
	int temprature1=Integer.parseInt(temp1);
	int temprature2=Integer.parseInt(temp2);
	if(temprature1<temprature2){
		tempratures[0]=temprature2;
		tempratures[1]=temprature1;
		return tempratures;
	}
	tempratures[0]=temprature1;
	tempratures[1]=temprature2;
	return tempratures;
}
/**
 * 如果返回值为-1,那么这个字符串中没有想要查找的字符
 * @return
 */
public static int getIndex(String temprature){
	return  temprature.indexOf("~");
}
public static int getCurrentTemprature(String temprature){
	if(temprature.indexOf("：")<0){
		return -1;
	}
	
	int changeTemp = temprature.indexOf("：");
	String temp1 = temprature.substring( changeTemp+1 ,temprature.length()-2).trim();
	return Integer.parseInt(temp1);
}
public static int getTemprature(String temprature){
	
	int changeTemp = temprature.indexOf("℃");
	String temp1 = temprature.substring( 0 ,changeTemp).trim();
	return Integer.parseInt(temp1);
	
}
public static int getChange(String String){
	int changeTemp = String.indexOf("转");
	return changeTemp;
}
}
