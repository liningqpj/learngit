package com.example.currentweather.Utils;



import com.example.currentweather.R;

import android.content.Context;

public class Constants {
	public static int picSize;
	public  Constants(Context c){
		picSize = (int) c.getResources().getDimension(R.dimen.picSize);
		System.out.println(picSize);
	}
  
}
