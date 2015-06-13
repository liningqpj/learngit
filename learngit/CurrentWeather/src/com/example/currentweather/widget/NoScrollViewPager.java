package com.example.currentweather.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 
 * @项目名:   CurrentWeather
 * @包名:    com.example.currentweather.widget
 * @类名:    NoScrollViewPager
 * @创建着:   李宁
 * @创建时间:  2015-5-12下午12:18:48
 * @描述:    不可以滑动的VIewPager
 */
public class NoScrollViewPager extends LazyViewPager
{

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public NoScrollViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
/*
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		// 不拦截
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		// 不消费
		return false;
	}*/

}
