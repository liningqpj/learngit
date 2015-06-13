package com.example.currentweather.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @项目名:   CurrentWeather
 * @包名:    com.example.currentweather
 * @类名:    BaseFragment
 * @创建着:   李宁
 * @创建时间:  2015-5-8下午4:35:19
 * @描述:    Fragment的基类
 */
public abstract class BaseFragment extends Fragment
{
	protected  Activity mActivity;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mActivity=getActivity();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return initView();
	}
	/**
	 * 子类自己去实现
	 * @return
	 */
	public abstract View initView();

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
	
		super.onActivityCreated(savedInstanceState);
		initData();
	}
	/**
	 * 如果孩子要加载数据,那么就重写这个方法
	 */
	protected void initData()
	{
		// TODO Auto-generated method stub
		
	}
}
