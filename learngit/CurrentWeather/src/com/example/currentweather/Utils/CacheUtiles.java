package com.example.currentweather.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @项目名:   CurrentWeather
 * @包名:    com.example.currentweather.Utils
 * @类名:    CacheUtiles
 * @创建着:   李宁
 * @创建时间:  2015-5-8上午10:20:40
 * @描述:   缓存数据工具类,主要用来存放SharedPreferences中的数据,和常量
 * 
 */
public class CacheUtiles
{
	public static String SPNAME="spname";//SharedPreferences的名字
	public static String  ISFIRSTLOAD="isFirstLoad";//是不是第一次使用这个软件
	public static String  CURRENTCITY="currentCity";//当前所在城市
	public static String WEATHER_DATAS="weatherDatas";//天气的缓存数据
	private static SharedPreferences	mSP;//
	/**
	 * 
	 * @param context 上下文
	 * @return 返回一个SharedPreferences的实列
	 */
	public static SharedPreferences getSP(Context context){
		if(mSP==null){
			mSP = context.getSharedPreferences(SPNAME,Context.MODE_PRIVATE );
		}
		return mSP;
	}
	/**
	 * 
	 * @param context  上下文
	 * @param keyName  缓存的数据的Key
	 * @return   返回Key对应的View 默认返回false
	 */
	public static boolean getBoolean(Context context,String keyName){
		
		SharedPreferences sp = getSP(context);
		
		return sp.getBoolean(keyName, false);
	}
	/**
	 * 
	 * @param context  上下文
	 * @param keyName   缓存的数据的Key
	 * @param value     要设置的值
	 */
	public static void  setBoolean(Context context,String keyName,boolean value){
		SharedPreferences sp = getSP(context);
		Editor edit = sp.edit();//获得Sp的编辑器
		edit.putBoolean(keyName, value).commit();
	}
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=
	public static String getString(Context context,String keyName){
		
		SharedPreferences sp = getSP(context);
		
		return sp.getString(keyName, "");
	}
	/**
	 * 
	 * @param context  上下文
	 * @param keyName   缓存的数据的Key
	 * @param value     要设置的值
	 */
	public static void  setString(Context context,String keyName,String value){
		SharedPreferences sp = getSP(context);
		Editor edit = sp.edit();//获得Sp的编辑器
		edit.putString(keyName, value).commit();
	}
	//*********************************************
	/**
	 * 通过SharedPreferences获得Long类型的数据，没有默认为-1
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 *            long值对应的key
	 * @return
	 */
	public static long getLong(Context context, String key)
	{
		mSP = getSP(context);
		return mSP.getLong(key, -1);
	}

	/**
	 * 通过SharedPreferences获得Long类型的数据，没有默认为-1，没有默认为传入的defValue
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 *             long值对应的key
	 * @param defValue
	 *            默认的long值
	 * @return
	 */
	public static long getLong(Context context, String key, long defValue)
	{
		mSP = getSP(context);
		return mSP.getLong(key, defValue);
	}

	/**
	 * 通过SharedPreferences获得Long类型的数据，没有默认为-1
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 *            long值对应的key
	 *@param value 
	 *            需要保持的Long值
	 */
	public static void setLong(Context context, String key, long value)
	{
		mSP = getSP(context);
		// 获得编辑器
		Editor mEdit = mSP.edit();
		mEdit.putLong(key, value).commit();
	}
}
