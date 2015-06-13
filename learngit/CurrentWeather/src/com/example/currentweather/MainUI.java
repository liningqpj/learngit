package com.example.currentweather;

import com.example.currentweather.fragment.ContentFragment;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

/**
 * 
 * @项目名:   CurrentWeather
 * @包名:    com.example.currentweather
 * @类名:    MainUI
 * @创建着:   李宁
 * @创建时间:  2015-5-8上午11:02:50
 * @描述:    主页面
 */
public class MainUI extends FragmentActivity
{
	private static final String	TAG_CONTENT	= "content";
	public static  int	screenWidth;
	public static  int	screenHeight;
@Override
protected void onCreate(Bundle savedInstanceState)
{
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	// // 去除title
	requestWindowFeature(Window.FEATURE_NO_TITLE);
			// // 去除状态栏
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	WindowManager.LayoutParams.FLAG_FULLSCREEN);
	screenWidth = getWindowManager().getDefaultDisplay().getWidth();
    screenHeight = getWindowManager().getDefaultDisplay().getHeight();
    SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5557342a");//注册讯飞语言识别
	initView();
}
/**
 * 初始化布局
 */
private void initView()
{
	
	setContentView(R.layout.activity_mainui);
	//加载Fragment布局
	initFragment();
}
private void initFragment()
{
	FragmentManager manager = getSupportFragmentManager();

	// 1.开启事务
	FragmentTransaction transaction = manager.beginTransaction();
	//加载主页面
	transaction.replace(R.id.main_container_content, new ContentFragment(), TAG_CONTENT);
	// 提交事务
	transaction.commit();
}
}
