package com.example.currentweather;

import java.util.ArrayList;

import com.example.currentweather.Utils.CacheUtiles;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @项目名: CurrentWeather
 * @包名: com.example.currentweather
 * @类名: GuideUI
 * @创建着: 李宁
 * @创建时间: 2015-5-8上午10:43:54
 * @描述: 引导界面
 */
public class GuideUI extends Activity implements OnClickListener, OnPageChangeListener
{
	private ViewPager				mPager;
	private TextView				guide_tv_one;
	private TextView				guide_tv_two;
	private TextView				guide_tv_three;
	private Button					guide_btn_start;
	private LinearLayout			guide_container_point;
	private View					guide_focus_point;
	private int						mPointSpace;			// 两个小圆点之间的距离
	private ArrayList<ImageView>	mPagerDatas;

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
		initView();
		initData();
	}

	/**
	 * 初始化布局
	 */
	private void initView()
	{

		setContentView(R.layout.activity_guideui);
		mPager = (ViewPager) findViewById(R.id.guide_pager);
		guide_tv_one = (TextView) findViewById(R.id.guide_tv_one);
		guide_tv_two = (TextView) findViewById(R.id.guide_tv_two);
		guide_tv_three = (TextView) findViewById(R.id.guide_tv_three);
		guide_btn_start = (Button) findViewById(R.id.guide_btn_start);
		guide_container_point = (LinearLayout) findViewById(R.id.guide_container_point);
		guide_focus_point = findViewById(R.id.guide_focus_point);
		guide_container_point.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout()
			{
				guide_container_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				mPointSpace = guide_container_point.getChildAt(1).getLeft() - guide_container_point.getChildAt(0).getLeft();
			}
		});

		// 给button设置点击事件
		guide_btn_start.setOnClickListener(this);
		// 给ViewPager设置滚动监听事件
		mPager.setOnPageChangeListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initData()
	{

		// 有ViewPager.首先就得想到数据

		int[] imgRes = new int[] {
				R.drawable.bg_guide_1,
				R.drawable.bg_guide_1,
				R.drawable.bg_guide_1
		};
		mPagerDatas = new ArrayList<ImageView>();
		ImageView iv;
		View point;
		for (int i = 0; i < imgRes.length; i++)
		{
			iv = new ImageView(GuideUI.this);
			iv.setImageResource(imgRes[i]);
			iv.setScaleType(ScaleType.FIT_XY);
			mPagerDatas.add(iv);
			point = new View(this);
			point.setBackgroundResource(R.drawable.guide_point_normal);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
			if (i != 0)
			{
				params.leftMargin = 20;
			}
			guide_container_point.addView(point, params);
		}
		mPager.setAdapter(new GuidePager());

	}

	/**
	 * 
	 * @描述: 适配器
	 */
	private class GuidePager extends PagerAdapter
	{

		@Override
		public int getCount()
		{
			if (mPagerDatas != null) { return mPagerDatas.size(); }
			return 0;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{

			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{

			// 展示ImageView
			ImageView iv = mPagerDatas.get(position);

			// 将ImageView加到ViewPager中
			container.addView(iv);

			// 返还ImageView
			return iv;
		}

	}

	/**
	 * 点击Button进入主界面
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View v)
	{
		//记录已经进入过引导页面,下次直接进主页面
		CacheUtiles.setBoolean(getApplicationContext(), CacheUtiles.ISFIRSTLOAD, true);
		//点击进入主页面
		Intent intent = new Intent(getApplicationContext(),MainUI.class);
		startActivity(intent);
		//关闭当前页面
		finish();

	}

	@Override
	public void onPageScrollStateChanged(int arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
	{
		// positionOffset:滑动的百分比
				// positionOffsetPixels:滑动的像素

				int leftMargin = (int) (mPointSpace * positionOffset + position * mPointSpace + 0.5f);

				RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) guide_focus_point.getLayoutParams();
				params.leftMargin = leftMargin;

				guide_focus_point.setLayoutParams(params);

	}

	@Override
	public void onPageSelected(int position)
	{
	/*	private TextView				guide_tv_one;
		private TextView				guide_tv_two;
		private TextView				guide_tv_three;*/
		// 设置显示或者隐藏
		switch (position)
		{
			case 0:
				guide_tv_one.setVisibility(View.VISIBLE);
				guide_tv_two.setVisibility(View.GONE);
				guide_tv_three.setVisibility(View.GONE);
				guide_btn_start.setVisibility(View.GONE);
			
				break;
			case 1:
				guide_tv_one.setVisibility(View.GONE);
				guide_tv_two.setVisibility(View.VISIBLE);
				guide_tv_three.setVisibility(View.GONE);
				guide_btn_start.setVisibility(View.GONE);
				break;
			case 2:
				guide_tv_one.setVisibility(View.GONE);
				guide_tv_two.setVisibility(View.GONE);
				guide_tv_three.setVisibility(View.VISIBLE);
				guide_btn_start.setVisibility(View.VISIBLE);
				
				break;

			default:
				break;
		}
	}
}
