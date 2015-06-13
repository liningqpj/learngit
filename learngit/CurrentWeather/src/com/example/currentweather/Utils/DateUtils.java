package com.example.currentweather.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.widget.ArrayAdapter;

/**
 * @项目名:   CurrentWeather
 * @包名:    com.example.currentweather.Utils
 * @类名:    DateUtils
 * @创建着:   李宁
 * @创建时间:  2015-5-16下午1:38:10
 * @描述:    TODO
 */
public class DateUtils
{
public static List<String> getPicDate(){
	List<String> list =new ArrayList<String>();
	//今天
	 Calendar calendar = Calendar.getInstance();  
	    int MONTH = calendar.get(Calendar.MONTH)+1;  
	    int DATE=calendar.get(Calendar.DATE);
	    if(MONTH<10&& DATE>=10){
	    	 list.add("0"+MONTH+"/"+DATE);
	    }else if(MONTH>=10 && DATE<10){
	    	 list.add(MONTH+"/0"+DATE);
	    }else  if(MONTH<10&& DATE<10){
	    	 list.add("0"+MONTH+"/0"+DATE);
	    }else{
	    	 list.add(MONTH+"/"+DATE);
	    }	   
	    //明天
	    calendar.add(calendar.DAY_OF_MONTH,1);
	    MONTH = calendar.get(Calendar.MONTH)+1;  
	    DATE=calendar.get(Calendar.DATE); 
	    if(MONTH<10&& DATE>=10){
	    	 list.add("0"+MONTH+"/"+DATE);
	    }else if(MONTH>=10 && DATE<10){
	    	 list.add(MONTH+"/0"+DATE);
	    }else  if(MONTH<10&& DATE<10){
	    	 list.add("0"+MONTH+"/0"+DATE);
	    }else{
	    	 list.add(MONTH+"/"+DATE);
	    }
	    //后天
	    calendar.add(calendar.DAY_OF_MONTH,1);
	    MONTH = calendar.get(Calendar.MONTH)+1;  
	    DATE=calendar.get(Calendar.DATE); 
	    if(MONTH<10&& DATE>=10){
	    	 list.add("0"+MONTH+"/"+DATE);
	    }else if(MONTH>=10 && DATE<10){
	    	 list.add(MONTH+"/0"+DATE);
	    }else  if(MONTH<10&& DATE<10){
	    	 list.add("0"+MONTH+"/0"+DATE);
	    }else{
	    	 list.add(MONTH+"/"+DATE);
	    }
	    //第四天
	    calendar.add(calendar.DAY_OF_MONTH,1);
	    MONTH = calendar.get(Calendar.MONTH)+1;  
	    DATE=calendar.get(Calendar.DATE); 
	    if(MONTH<10&& DATE>=10){
	    	 list.add("0"+MONTH+"/"+DATE);
	    }else if(MONTH>=10 && DATE<10){
	    	 list.add(MONTH+"/0"+DATE);
	    }else  if(MONTH<10&& DATE<10){
	    	 list.add("0"+MONTH+"/0"+DATE);
	    }else{
	    	 list.add(MONTH+"/"+DATE);
	    }
	    
	return list;
}

}
