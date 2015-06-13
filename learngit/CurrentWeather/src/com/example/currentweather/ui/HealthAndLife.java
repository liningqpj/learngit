package com.example.currentweather.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.currentweather.DetailUI;
import com.example.currentweather.MainUI;
import com.example.currentweather.R;
import com.example.currentweather.Utils.CacheUtiles;
import com.example.currentweather.base.TabController;
import com.example.currentweather.bean.bean;
import com.example.currentweather.xlistview.XListView;
import com.example.currentweather.xlistview.XListView.IXListViewListener;

/**
 * @项目名: CurrentWeather
 * @包名: com.example.currentweather.ui
 * @类名: WeatherContent
 * @创建着: 李宁
 * @创建时间: 2015-5-8下午7:06:15
 * @描述: TODO
 */
public class HealthAndLife extends TabController implements IXListViewListener, OnItemClickListener,OnPageChangeListener
{

	private XListView			mListView;
	private ArrayList<bean>	items		= new ArrayList<bean>();
	private Handler				mHandler;
	private int					start		= 0;
	private static int			refreshCnt	= 0;
	private MyAdapter			mAdapter;
	private ViewPager			mViewPager;
	private AutoSwitchPicTask	mAutoSwitchTask;						// 封装后的Handler
	private List<Bitmap>		mPagerDatas;
	private TextView	news_list_tv_title;//轮播 每个图的标题
	private LinearLayout	news_list_container_point;//轮播图中的点
	private List<String>	picTitle;
	public static String url="http://www.sznews.com/travel/node_38941.htm";
	private List<bean>	list;
	private int listItem=10;//ListView的条目
	public HealthAndLife(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		list = new ArrayList<bean>();
	
		
	}
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what)
			{
				case 0:
					Toast.makeText(mContext, "获取数据失败，请检查网络...", 0).show();
					break;
                case 1:
                	geneItems();
                	mAdapter = new MyAdapter();
            		mListView.setAdapter(mAdapter);
            		mListView.setXListViewListener(HealthAndLife.this);
            		mListView.setOnItemClickListener(HealthAndLife.this);
            		mHandler = new Handler();
					break;

				default:
					break;
			}
		};
	};

	@Override
	protected View initContentView(Context context)
	{
		View view = View.inflate(mContext, R.layout.activity_health, null);
		mViewPager = (ViewPager) view.findViewById(R.id.health_vp);
		mListView = (XListView) view.findViewById(R.id.xlistview);
		news_list_container_point = (LinearLayout) view.findViewById(R.id.news_list_container_point);
		news_list_tv_title = (TextView) view.findViewById(R.id.news_list_tv_title);
		
		
		mListView.setPullLoadEnable(true);
        //获取ListView的数据
		getListViewDatas();
		/*mAdapter = new MyAdapter();
		mListView.setAdapter(mAdapter);
		mListView.setXListViewListener(this);
		mHandler = new Handler();*/
		return view;
	}
	private Message	msg;
	private void getListViewDatas()
	{
		new Thread(){
		public void run() {
			 try
			{
				 msg = Message.obtain();
				Document doc = Jsoup.connect(url).timeout(30000).get();
				 Elements root =doc.getElementsByClass("txtul");
				 System.out.println(root.size()+"***************************************");
				 for (int i = 0; i < root.size(); i++)
				{
					
					 Element info = root.get(i);
					 Elements li = info.getElementsByTag("li");
					 for (int j = 0; j < li.size(); j++)
					{
						 bean bean = new  bean();
						 Elements time = li.get(j).getElementsByTag("span");
						
						 bean.time=time.get(0).text();//获得新闻的时间
						 Elements a = li.get(j).getElementsByTag("a");
						 Elements attribute = a.get(0).getElementsByAttribute("href");
						 Element string = attribute.get(0);
						 bean.url=string.absUrl("abs:href");
						 bean.text=a.get(0).text();//活的文字信息
						 
					list.add(bean);
					}
				}
			for (int i = 0; i < list.size(); i++)
				{
					bean bean = list.get(i);
					System.out.println(bean.time+"---------"+bean.text);
					System.out.println(bean.url);
				}
			msg.what=1;
			handler.sendMessage(msg);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("出现异常。。。。。");
				msg=Message.obtain();
				msg.what=0;
				handler.sendMessage(msg);
			}
			
		};}.start();
	}
	@Override
	public void initData()
	{

		mPagerDatas = new ArrayList<Bitmap>();
		picTitle = new ArrayList<String>();
		picTitle.add(" 1. 水边嬉戏");
		picTitle.add(" 2. 瑜伽");
		picTitle.add(" 3. 爬山");
		picTitle.add(" 4. 骑行");
		/*
		 * BitmapFactory.Options options = new BitmapFactory.Options();
		 * options.inJustDecodeBounds=true;
		 * BitmapFactory.decodeResource(mContext
		 * .getResources(),R.drawable.bitmap1,options);
		 * int imageHeight = options.outHeight;
		 * int imageWidth = options.outWidth;
		 * String imageType = options.outMimeType;
	*/
		int screenHeight = MainUI.screenHeight;
	 int screenWidth = MainUI.screenWidth; 
		
		Bitmap bitmap1 = decodeSampledBitmapFromResource(mContext.getResources(), R.drawable.bitmap1, 
		                                screenWidth, screenHeight/3);
		Bitmap bitmap2 = decodeSampledBitmapFromResource(mContext.getResources(), R.drawable.bitmap2, 
		         		                                screenWidth, screenHeight/3);
		Bitmap bitmap3 = decodeSampledBitmapFromResource(mContext.getResources(), R.drawable.bitmap3, 
		         		                                screenWidth, screenHeight/3);
		Bitmap bitmap4 = decodeSampledBitmapFromResource(mContext.getResources(), R.drawable.bitmap4, 
		         		                                screenWidth, screenHeight/3);
		
		mPagerDatas.add(bitmap1);
		mPagerDatas.add(bitmap2);
		mPagerDatas.add(bitmap3);
		mPagerDatas.add(bitmap4);
		// 去动态的添加点
				for (int i = 0; i < mPagerDatas.size(); i++)
				{
					View point = new View(mContext);
					point.setBackgroundResource(R.drawable.dot_normal);

					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
					if (i != 0)
					{
						params.leftMargin = 20;
					}
					else
					{
						// 设置默认的图片
						point.setBackgroundResource(R.drawable.dot_focus);
					}
					// 添加点
					news_list_container_point.addView(point, params);
				}
		mViewPager.setAdapter(new PicAdapter());
		// 开启轮播图
		if (mAutoSwitchTask == null)
		{
			mAutoSwitchTask = new AutoSwitchPicTask();
		}
		mAutoSwitchTask.start();
		// 添加ViewPager的监听
		mViewPager.setOnPageChangeListener(this);
		// 设置ViewPager的touch的监听
		mViewPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event)
			{

				switch (event.getAction())
				{
					case MotionEvent.ACTION_DOWN:
						System.out.println("按下去，停止轮播");
						// 如果手指按下去时，希望轮播停止，
						mAutoSwitchTask.stop();
						break;
					case MotionEvent.ACTION_UP:
					case MotionEvent.ACTION_CANCEL:

						System.out.println("抬起，开始轮播");
						// 如果手指抬起时，图片进行轮播
						mAutoSwitchTask.start();
						break;
					default:
						break;
				}

				return false;
			}
		});
		super.initData();
	}

	private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
	{
		// 源图片的高度和宽度
		int Height = options.outHeight;
		int Width = options.outWidth;
		int inSampleSize = 1;
		// 如果源图片过大
		if (Height > reqHeight || Width > reqWidth)
		{
			int heightRatio = Math.round((float) Height / (float) reqHeight);
			int widthRatio = Math.round((float) Width / (float) reqWidth);
			//  选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高  
			//  一定都会大于等于目标的宽和高。
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	private Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight)
	{
		//  第一次解析将inJustDecodeBounds设置为true，来获取图片大小
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		//  调用上面定义的方法计算inSampleSize值
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		//  使用获取到的inSampleSize值再次解析图片
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);

	}

	/*
	 * 1.public static Bitmap decodeSampledBitmapFromResource(Resources res, int 
	 * resId,   2.        int reqWidth, int reqHeight) {  
	 * 3.    // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
	 * 4.    final BitmapFactory
	 * .Options options = new BitmapFactory.Options();  
	 * 5.    options.inJustDecodeBounds = true;  
	 * 6.    BitmapFactory.decodeResource(res, resId, options);  
	 * 7.    // 调用上面定义的方法计算inSampleSize值  
	 * 8.    options.inSampleSize = calculateInSampleSize
	 * (options, reqWidth, reqHeight);   9.    // 使用获取到的inSampleSize值再次解析图片  
	 * 10.    options.inJustDecodeBounds = false;  
	 * 11.    return BitmapFactory.decodeResource(res, resId, options);   12.}  
	 */
	class PicAdapter extends PagerAdapter
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
			// TODO Auto-generated method stub
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
			ImageView iv = new ImageView(mContext);
			iv.setScaleType(ScaleType.FIT_XY);

			// 设置iv的image，设置默认值
			iv.setImageBitmap(mPagerDatas.get(position));
			container.addView(iv);

			return iv;
		}

	}

	/** Called when the activity is first created. */
	private void geneItems()
	{
		items.clear();
		if(listItem>10){
			listItem=10;
			Toast.makeText(mContext, "没有更多数据", 0).show();
		}
		for (int i = 0; i < listItem; ++i)
		{
			items.add(list.get(i));
		}
		listItem+=5;
	}

	private void onLoad()
	{
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

	@Override
	public void onRefresh()
	{
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run()
			{
				start = ++refreshCnt;
				items.clear();

				// mAdapter.notifyDataSetChanged();

				mListView.setAdapter(mAdapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore()
	{
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run()
			{
				geneItems();
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}
	private class ViewHolder{
		TextView lv_title;
		TextView lv_time;
		TextView lv_url;
	}

	private class MyAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			if (items != null) { return items.size(); }
			return 0;
		}

		@Override
		public Object getItem(int position)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position)
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			ViewHolder holder = null;
			if(convertView==null){
				// 没有复用
				holder = new ViewHolder();
				convertView = View.inflate(mContext, R.layout.list_item, null);
				
				// 设置tag
				convertView.setTag(holder);
				
				// 初始化view
				holder.lv_title = (TextView) convertView.findViewById(R.id.news_lv_title);
				holder.lv_time = (TextView) convertView.findViewById(R.id.news_lv_time);
				holder.lv_url = (TextView) convertView.findViewById(R.id.news_lv_url);
			}else{
				// 复用
				holder = (ViewHolder) convertView.getTag();
			}
			bean bean = list.get(position);

			// 设置数据
			holder.lv_title.setText(bean.text);
			holder.lv_time.setText(bean.time);
			holder.lv_url.setText("");
			
			return convertView;
		}

	}

	// 轮播图
	class AutoSwitchPicTask extends Handler implements Runnable
	{
		/**
		 * 开启任务
		 */
		public void start()
		{
			stop();
			postDelayed(this, 3000);
		}

		/**
		 * 关闭任务
		 */
		public void stop()
		{
			removeCallbacks(this);
		}

		@Override
		public void run()
		{
			// ViewPager选中下一个，如果是最后一个就选中第一个

			int position = mViewPager.getCurrentItem();
			if (position != mViewPager.getAdapter().getCount() - 1)
			{
				// 选中下一个
				mViewPager.setCurrentItem(++position);
			}
			else
			{
				// 如果是最后一个就选中第一个
				mViewPager.setCurrentItem(0);
			}

			// 发送延时任务
			postDelayed(this, 3000);
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0)
	{
		//设置监听事件，当页面选中时 
		// 设置选中的点
		int count = 4;
		for (int i = 0; i < count; i++)
		{
			View view = news_list_container_point.getChildAt(i);
			view.setBackgroundResource(arg0 == i ? R.drawable.dot_focus : R.drawable.dot_normal);
		}
		// 设置文本
		news_list_tv_title.setText(picTitle.get(arg0));		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		
		if(list==null){
			return;
		}
		//设置已读的缓存数据
		bean bean=list.get(position-1);
		System.out.println("position"+position+"**********************");
		// 缓存已读的数据
				String title = bean.text;
		System.out.println(title);
		
		/*CacheUtils.setBoolean(mContext, title, true);
		// 通知UI刷新
		mListViewAdapter.notifyDataSetChanged();*/
		Intent intent = new Intent(mContext,DetailUI.class);
		intent.putExtra(DetailUI.KEY_URL, bean.url);
		
		mContext.startActivity(intent);
		
	}
	
}
