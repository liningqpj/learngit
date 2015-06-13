package com.example.currentweather.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.currentweather.R;
import com.example.currentweather.Utils.IconUtils;
import com.example.currentweather.base.BaseFragment;
import com.example.currentweather.base.TabController;
import com.example.currentweather.ui.HealthAndLife;
import com.example.currentweather.ui.Setting;
import com.example.currentweather.ui.WeatherContent;
import com.example.currentweather.widget.AutoHeightViewPager;
import com.example.currentweather.widget.NoScrollViewPager;

import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @项目名:   CurrentWeather
 * @包名:    com.example.currentweather.fragment
 * @类名:    ContentFragment
 * @创建着:   李宁
 * @创建时间:  2015-5-8下午4:50:32
 * @描述:    主页面的Fragment
 */
public class ContentFragment extends BaseFragment implements OnCheckedChangeListener
{
	
	private ViewPager	contentViewPager;
	private RadioGroup	content_rg;
	private RadioButton	mRbweather;
	private RadioButton	mRbhealth;
	private RadioButton	mRbsetting;
	private List<TabController>	mContentViewPagerDatas;
	private int					mCurrentTab;//当前选中的页面

	@Override
	public View initView()
	{
		View view = View.inflate(mActivity, R.layout.content, null);
		contentViewPager = (ViewPager) view.findViewById(R.id.content_vp);
		content_rg = (RadioGroup) view.findViewById(R.id.content_rg);
		
/*		mRbweather = (RadioButton) view.findViewById(R.id.content_rb_weather);
		mRbhealth = (RadioButton) view.findViewById(R.id.content_rb_health);
		mRbsetting = (RadioButton) view.findViewById(R.id.content_rb_setting);*/
			 return view;
	}
	/**
	 * 数据初始化
	 */
	@Override
	protected void initData()
	{
		mContentViewPagerDatas = new ArrayList<TabController>();
		mContentViewPagerDatas.add(new WeatherContent(mActivity));
		mContentViewPagerDatas.add(new HealthAndLife(mActivity));
		mContentViewPagerDatas.add(new Setting(mActivity));
		
	
		// 给ViewPager去加载数据
		contentViewPager.setAdapter(new ContentPagerAdapter());// adapter ---> list<数据类型>
	
	
	
		content_rg.setOnCheckedChangeListener(this);
		mCurrentTab = 0;
		content_rg.check(R.id.content_rb_weather);
		
	
	}

private class ContentPagerAdapter extends PagerAdapter{

	@Override
	public int getCount()
	{
		if(mContentViewPagerDatas!=null){
			return mContentViewPagerDatas.size();
		}
		return 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1)
	{
		return arg0==arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object)
	{
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position)
	{
		TabController controller = mContentViewPagerDatas.get(position);

		// 获得视图
		View rootView = controller.getRootView();
		container.addView(rootView);

		// 设置数据
		controller.initData();

		return rootView;
		
	}
	
}
@Override
public void onCheckedChanged(RadioGroup group, int checkedId)
{
	switch (checkedId)
	{
		case (R.id.content_rb_weather):
			mCurrentTab=0;
			break;
	    case (R.id.content_rb_health):
	    	mCurrentTab=1;
			break;
	  case (R.id.content_rb_setting):
		  mCurrentTab=2;
		break;

		default:
			break;
	}
	contentViewPager.setCurrentItem(mCurrentTab);
	
}

}
