package com.example.currentweather;


import com.example.currentweather.Utils.CacheUtiles;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDNotifyListener;//假如用到位置提醒功能，需要import该类
/**
 * 
 * @项目名:   CurrentWeather
 * @包名:    com.example.currentweather
 * @类名:    WelcomeUI
 * @创建着:   李宁
 * @创建时间:  2015-5-8上午10:17:55
 * @描述:    欢迎界面
 */
public class WelcomeUI extends Activity
{

	private static final long	SPLASH_TIME_OUT	= 2000;
	public LocationClient		mLocationClient	= null;
	public BDLocationListener	myListener		= new MyLocationListener();
	public long Location_delay=2000;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// // 去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
				// // 去除状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		
		//第一次进入,需要定位当前的城市,然后再去获取城市的天气数据
		initLocation();
		//开启子线程 让splash展示一段时间
	
	    }
	
  private void initLocation()
	{
	String string = CacheUtiles.getString(getApplicationContext(), CacheUtiles.CURRENTCITY);
	//Toast.makeText(getApplicationContext(), "当前城市"+string, 0).show();
	if(TextUtils.isEmpty(string)){
			//如果进来,那么就是没有城市的信息
			//需要进行定位 
	//System.out.println("进入定位的方法");
//		延长等待时间
		Location_delay=6000;
		showDialog();
		StartGetLocation();		
		}
	
		//不需要定位的话直接进入主页面
		 new Handler().postDelayed(new Runnable() {
	            @Override
	            public void run() {
	                // This method will be executed once the timer is over
	                // Start your app main activity	               
	            	CheckIsFirstLoading();
	                overridePendingTransition(0, 0);
	                //Details.this.overridePendingTransition(R.anim.nothing,R.anim.nothing);
	            }
	        }, Location_delay);
	}
  private AlertDialog.Builder builder;
private AlertDialog dialog;
  //自定义的对话框
  private void showDialog(){
	  builder=new Builder(WelcomeUI.this); 
	  View view = View.inflate(WelcomeUI.this, R.layout.dialog_location, null);
	  builder.setView(view);
	  dialog = builder.show();	  
  }
/**
 * 进行定位
 */
private void StartGetLocation()
{
	
	mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类

	

	mLocationClient.registerLocationListener(myListener); // 注册监听函数

	LocationClientOption option = new LocationClientOption();

	option.setOpenGps(true);

	option.setAddrType("all");// 返回的定位结果包含地址信息

	option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02

	option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms

	option.disableCache(true);// 禁止启用缓存定位

	option.setPoiNumber(1); // 最多返回POI个数

	//option.setPoiDistance(1000); // poi查询距离

	//option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息

	mLocationClient.setLocOption(option);
	// 发起定位请求
	mLocationClient.requestLocation();
	System.out.println("发起定位请求");
}

/**
 * 判断是否是第一次登陆,如果是那么就进入引导界面,
 * 如果不是,那么就直接进入主页面
 */
	private void CheckIsFirstLoading()
	{
		//通过获取缓存数据进行判断
		boolean isFirstLoad = CacheUtiles.getBoolean(getApplicationContext(), CacheUtiles.ISFIRSTLOAD);
		if(!isFirstLoad){
			//进来表示是第一次,那么进入引导页面
			Intent intent = new Intent(getApplicationContext(),GuideUI.class);		
			startActivity(intent);
			
			//System.out.println("进入引导页面");
			
		}else{
   //			直接进入主页面,在进入之前需要进行定位,把定位后得到的城市信息带过去
			Intent intent = new Intent(getApplicationContext(),MainUI.class);
			startActivity(intent);
		
		}
		//关闭当前页面
		finish();
		
	}
	/**
	 * 百度地图定位的回调
	 */
	public class MyLocationListener implements BDLocationListener
	{
		@Override
		public void onReceiveLocation(BDLocation location)
		{
			if (location == null) return;		
			String city = location.getCity();
			System.out.println("当前城市"+city);
			//设置数据
			CacheUtiles.setString(getApplicationContext(), CacheUtiles.CURRENTCITY,city);		
	
		}
		public void onReceivePoi(BDLocation poiLocation)
		{	
		}
	}
	/**
	 * 将百度地图的与Activity的生命周期进行绑定
	 */
	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		if(mLocationClient!=null){
			mLocationClient.stop();
		}
	
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		if(mLocationClient!=null){
			mLocationClient.start();
		}
		
	
		super.onResume();
	}
	@Override
	protected void onDestroy()
	{
		if(dialog!=null){
			dialog.dismiss();
		}
		super.onDestroy();
	}
}
