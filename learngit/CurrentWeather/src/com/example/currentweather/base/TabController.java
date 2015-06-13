package com.example.currentweather.base;


import com.example.currentweather.R;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @项目名:   CurrentWeather
 * @包名:    com.example.currentweather.base
 * @类名:    TabController
 * @创建着:   李宁
 * @创建时间:  2015-5-8下午6:41:17
 * @描述:    页面对应的控制器
 */
public abstract class TabController
{
	protected Context mContext;
	private View mRootView;//根布局
	private FrameLayout	mContentContainer;
	public TabController(Context context){
		this.mContext=context;
		 mRootView = initView(context);
	}
	/**
	 * 初始化视图
	 * @return
	 */
	private View initView(Context context){
		View view = View.inflate(mContext, R.layout.base_tab, null);
		mContentContainer = (FrameLayout) view.findViewById(R.id.tab_container_content);
		//向容器内添加布局
		mContentContainer.addView(initContentView(context));
		return view;
	}

	/**
	 * 初始化内容的view
	 * 
	 * @return
	 */
	protected abstract View initContentView(Context context);
	/**
	 * 
	 * @return 获得根布局
	 */
	public View getRootView(){
		return mRootView;
	}
	/**
	 * 获取上下文
	 * 
	 * @return
	 */
	public Context getContext()
	{
		return mContext;
	}

	/**
	 * 初始化数据的方法，孩子如果有数据初始化，就复写
	 */
	public void initData()
	{

	}
}
