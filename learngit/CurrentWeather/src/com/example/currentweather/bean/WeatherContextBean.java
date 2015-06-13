package com.example.currentweather.bean;

import java.util.List;

/**
 * @项目名:   CurrentWeather
 * @包名:    com.example.currentweather.bean
 * @类名:    WeatherContextBean
 * @创建着:   李宁
 * @创建时间:  2015-5-13下午8:19:53
 * @描述:    存Json数据的bean
 */
public class WeatherContextBean
{
	public String date;
	public int error;
	public List<Info> results;
	public String status;
	
	public class Info{
		public String currentCity;
		public int pm25;
		public List<Wear> index;
		public  List<Weather> weather_data;
	}
	
	
	public class Wear{
		public String des;// "天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"
		public String tipt;//	穿衣指数
		public String title	;//穿衣
		public String zs;//	热	
	}
	public class Weather{
		public String date;//周三 05月13日 (实时：29℃)
		public String dayPictureUrl;//	http://api.map.baidu.com/images/weather/day/yin.png
		public String nightPictureUrl; //	http://api.map.baidu.com/images/weather/night/yin.png
		public String temperature;//	19℃
		public String weather;	//阴
		public String wind; //	北风4-5级
	}	
}
