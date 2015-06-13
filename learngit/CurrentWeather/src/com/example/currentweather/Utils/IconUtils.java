package com.example.currentweather.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.currentweather.R;



/**
 * @项目名:   CurrentWeather
 * @包名:    com.example.currentweather.Utils
 * @类名:    IconUtils
 * @创建着:   李宁
 * @创建时间:  2015-5-13下午10:23:09
 * @描述:    TODO
 */
public class IconUtils
{
	//获得当前时间
	public static String getCurTime() {
		String date = null;
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		date = format.format(c.getTime());
		return date;
	}
	public static int getTimeType(){
		
		if (getCurTime().compareTo("18:00") < 0
		&& getCurTime().compareTo("05:00") > 0)// 18:00-05:00算黑夜
{

	return 0;
}

return 1;
	
	}


	public static int parseIcon( String strIcon) {
		int time=getTimeType();
		if (strIcon == null)
			return -1;
		if (time == 0) {
			if ("晴".equals(strIcon))
				return R.drawable.wether_ico00;
			if ("多云".equals(strIcon))
				return R.drawable.wether_ico01;
			if ("阴".equals(strIcon))
				return R.drawable.wether_ico02;
			if ("阵雨".equals(strIcon))
				return R.drawable.wether_ico03;
			if ("雷阵雨".equals(strIcon))
				return R.drawable.wether_ico04;
			if ("雷阵雨伴有冰雹".equals(strIcon))
				return R.drawable.wether_ico05;
			if ("雨夹雪".equals(strIcon))
				return R.drawable.wether_ico06;
			if ("小雨".equals(strIcon))
				return R.drawable.wether_ico07;
			if ("中雨".equals(strIcon))
				return R.drawable.wether_ico08;
			if ("大雨".equals(strIcon))
				return R.drawable.wether_ico09;
			if ("暴雨".equals(strIcon))
				return R.drawable.wether_ico10;
			if ("大暴雨".equals(strIcon))
				return R.drawable.wether_ico11;
			if ("特大暴雨".equals(strIcon))
				return R.drawable.wether_ico12;
			if ("阵雪".equals(strIcon))
				return R.drawable.wether_ico13;
			if ("小雪".equals(strIcon))
				return R.drawable.wether_ico14;
			if ("中雪".equals(strIcon))
				return R.drawable.wether_ico15;
			if ("大雪".equals(strIcon))
				return R.drawable.wether_ico16;
			if ("暴雪".equals(strIcon))
				return R.drawable.wether_ico17;
			if ("雾".equals(strIcon))
				return R.drawable.wether_ico18;
			if ("冻雨".equals(strIcon))
				return R.drawable.wether_ico19;
			if ("沙尘暴".equals(strIcon))
				return R.drawable.wether_ico20;
			if ("小雨转中雨".equals(strIcon))
				return R.drawable.wether_ico21;
			if ("中雨转大雨".equals(strIcon))
				return R.drawable.wether_ico22;
			if ("大雨转暴雨".equals(strIcon))
				return R.drawable.wether_ico23;
			if ("暴雨转大暴雨".equals(strIcon))
				return R.drawable.wether_ico24;
			if ("大暴雨转特大暴雨".equals(strIcon))
				return R.drawable.wether_ico25;
			if ("小雪转中雪".equals(strIcon))
				return R.drawable.wether_ico26;
			if ("中雪转大雪".equals(strIcon))
				return R.drawable.wether_ico27;
			if ("大雪转暴雪".equals(strIcon))
				return R.drawable.wether_ico28;
			if ("浮尘".equals(strIcon))
				return R.drawable.wether_ico29;
			if ("扬沙".equals(strIcon) || "霾".equals(strIcon))
				return R.drawable.wether_ico30;
			if ("强沙尘暴".equals(strIcon))
				return R.drawable.wether_ico31;
			return R.drawable.wether_ico00;
		} else {
			if (strIcon.indexOf("晴") > 0)
				return R.drawable.wether_ico32;
			if (strIcon.indexOf("云") > 0)
				return R.drawable.wether_ico33;
			if (strIcon.indexOf("雨") > 0)
				return R.drawable.wether_ico34;
			if (strIcon.indexOf("雪") > 0)
				return R.drawable.wether_ico35;
			if (strIcon.indexOf("雷") > 0)
				return R.drawable.wether_ico36;
			if (strIcon.indexOf("冰") > 0)
				return R.drawable.wether_ico37;
			return R.drawable.wether_ico32;
		}
		// return 0;
	}
	public static int getRid( String strIcon,int type) {
		int time=type;
		if (strIcon == null)
			return -1;
		if (time == 0) {
			if ("晴".equals(strIcon))
				return R.drawable.wether_ico00;
			if ("多云".equals(strIcon))
				return R.drawable.wether_ico01;
			if ("阴".equals(strIcon))
				return R.drawable.wether_ico02;
			if ("阵雨".equals(strIcon))
				return R.drawable.wether_ico03;
			if ("雷阵雨".equals(strIcon))
				return R.drawable.wether_ico04;
			if ("雷阵雨伴有冰雹".equals(strIcon))
				return R.drawable.wether_ico05;
			if ("雨夹雪".equals(strIcon))
				return R.drawable.wether_ico06;
			if ("小雨".equals(strIcon))
				return R.drawable.wether_ico07;
			if ("中雨".equals(strIcon))
				return R.drawable.wether_ico08;
			if ("大雨".equals(strIcon))
				return R.drawable.wether_ico09;
			if ("暴雨".equals(strIcon))
				return R.drawable.wether_ico10;
			if ("大暴雨".equals(strIcon))
				return R.drawable.wether_ico11;
			if ("特大暴雨".equals(strIcon))
				return R.drawable.wether_ico12;
			if ("阵雪".equals(strIcon))
				return R.drawable.wether_ico13;
			if ("小雪".equals(strIcon))
				return R.drawable.wether_ico14;
			if ("中雪".equals(strIcon))
				return R.drawable.wether_ico15;
			if ("大雪".equals(strIcon))
				return R.drawable.wether_ico16;
			if ("暴雪".equals(strIcon))
				return R.drawable.wether_ico17;
			if ("雾".equals(strIcon))
				return R.drawable.wether_ico18;
			if ("冻雨".equals(strIcon))
				return R.drawable.wether_ico19;
			if ("沙尘暴".equals(strIcon))
				return R.drawable.wether_ico20;
			if ("小雨转中雨".equals(strIcon))
				return R.drawable.wether_ico21;
			if ("中雨转大雨".equals(strIcon))
				return R.drawable.wether_ico22;
			if ("大雨转暴雨".equals(strIcon))
				return R.drawable.wether_ico23;
			if ("暴雨转大暴雨".equals(strIcon))
				return R.drawable.wether_ico24;
			if ("大暴雨转特大暴雨".equals(strIcon))
				return R.drawable.wether_ico25;
			if ("小雪转中雪".equals(strIcon))
				return R.drawable.wether_ico26;
			if ("中雪转大雪".equals(strIcon))
				return R.drawable.wether_ico27;
			if ("大雪转暴雪".equals(strIcon))
				return R.drawable.wether_ico28;
			if ("浮尘".equals(strIcon))
				return R.drawable.wether_ico29;
			if ("扬沙".equals(strIcon) || "霾".equals(strIcon))
				return R.drawable.wether_ico30;
			if ("强沙尘暴".equals(strIcon))
				return R.drawable.wether_ico31;
			return R.drawable.wether_ico00;
		} else {
			if (strIcon.indexOf("晴") > 0)
				return R.drawable.wether_ico32;
			if (strIcon.indexOf("云") > 0)
				return R.drawable.wether_ico33;
			if (strIcon.indexOf("雨") > 0)
				return R.drawable.wether_ico34;
			if (strIcon.indexOf("雪") > 0)
				return R.drawable.wether_ico35;
			if (strIcon.indexOf("雷") > 0)
				return R.drawable.wether_ico36;
			if (strIcon.indexOf("冰") > 0)
				return R.drawable.wether_ico37;
			return R.drawable.wether_ico32;
		}
		// return 0;
	}
	
}
