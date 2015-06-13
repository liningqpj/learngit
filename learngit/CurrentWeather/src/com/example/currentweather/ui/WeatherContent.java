package com.example.currentweather.ui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.currentweather.MainUI;
import com.example.currentweather.R;
import com.example.currentweather.SelectotCity;
import com.example.currentweather.Utils.CacheUtiles;
import com.example.currentweather.Utils.Constants;
import com.example.currentweather.Utils.DateUtils;
import com.example.currentweather.Utils.IconUtils;
import com.example.currentweather.Utils.JsoupUtils;
import com.example.currentweather.Utils.TemperatureUtils;
import com.example.currentweather.base.TabController;
import com.example.currentweather.bean.WeatherContextBean;

import com.example.currentweather.bean.WeatherContextBean.Info;
import com.example.currentweather.bean.WeatherContextBean.Wear;
import com.example.currentweather.bean.WeatherContextBean.Weather;
import com.example.currentweather.widget.TrendView;
import com.google.gson.Gson;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @项目名: CurrentWeather
 * @包名: com.example.currentweather.ui
 * @类名: WeatherContent
 * @创建着: 李宁
 * @创建时间: 2015-5-8下午7:06:15
 * @描述: TODO
 */
public class WeatherContent extends TabController implements OnClickListener
{

	protected static final int	DATE		= 0;		// 今天的日期
	private static final String	TextView	= null;
	private LinearLayout		mWeather_bg;			// 整个天气界面的背景
	private ImageView			mHome;					// 点击进入设置位置
	private TextView			mCity;					// 当前的城市名
	private Button				btn_refersh;			// 刷新数据
	private TextView			mDate;					// 当前日期
	private TextView			mRefersh_time;			// 刷新时间
	private ImageView			mCurrentweather_icon;	// 天气的图标
	private TextView			mWeather_state;		// 今天天气的状态
	private TextView			tvcurrent_T;			// 今天的温度

	private String				currentCity;			// 当前城市
	private String				city_str	= "";
	private TextView			mWind_set;				// 风力
	private TextView			pm_set;				// PM
	private TextView			weather_des;			// 天气描述
	private TrendView			mTrendView;
	private TextView			day1;
	private TextView			day2;
	private TextView			day3;
	private TextView			day4;
	private TextView			wea1;
	private TextView			wea2;
	private TextView			wea3;
	private TextView			wea4;
	private List<String>		date;
	private List<String>		listWeather;
	private List<Integer>		top;					// 一天的最高温度
	private List<Integer>		low;					// 一天中的低温
	private List<Integer>		topPic;				// 白天对应的图片
	private List<Integer>		lowPic;				// 晚上对应的图片
	private RelativeLayout		mRtHead_bg;			// 最上面的头布局
	private TextView			mWear_cloth;			// 穿衣指数
	private TextView			mClean_car;			// 洗车指数
	private TextView			mTime1;				// 日期
	private TextView			mTime2;
	private TextView			mTime3;
	private TextView			mTime4;
	private int currentWendu=0;//实时温度
	private ProgressBar	pb_refersh;//刷新进度条
	private ImageView	voice_speak;
	
	public WeatherContent(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected View initContentView(Context context)
	{
		View view = View.inflate(mContext, R.layout.weather_main, null);
		mWeather_bg = (LinearLayout) view.findViewById(R.id.weather_bg);
		mRtHead_bg = (RelativeLayout) view.findViewById(R.id.rt_head_bg);
		mHome = (ImageView) view.findViewById(R.id.iv_home);
		mCity = (TextView) view.findViewById(R.id.tv_city);
		btn_refersh = (Button) view.findViewById(R.id.btn_refersh);
		pb_refersh = (ProgressBar) view.findViewById(R.id.pb_refersh);
		mDate = (TextView) view.findViewById(R.id.tv_date);
		mRefersh_time = (TextView) view.findViewById(R.id.tv_refersh_time);
		mCurrentweather_icon = (ImageView) view.findViewById(R.id.iv_currentweather_icon);
		mWeather_state = (TextView) view.findViewById(R.id.tv_wt_state);
		tvcurrent_T = (TextView) view.findViewById(R.id.tv_current_T);
		mWind_set = (TextView) view.findViewById(R.id.tv_wind_set);
		pm_set = (TextView) view.findViewById(R.id.pm_set);
		weather_des = (TextView) view.findViewById(R.id.weather_des);
		mWear_cloth = (android.widget.TextView) view.findViewById(R.id.tv_wear_cloth);
		mClean_car = (android.widget.TextView) view.findViewById(R.id.tv_clean_car);
		voice_speak = (ImageView) view.findViewById(R.id.voice_speak);

		/*
		 * int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		 * int screenHeight =
		 * getWindowManager().getDefaultDisplay().getHeight();
		 */
		int screenWidth = MainUI.screenWidth;
		int screenHeight = (int) (MainUI.screenHeight * 0.7);
		mTrendView = (TrendView) view.findViewById(R.id.trendView);
		mTrendView.setWidthHeight(screenWidth, screenHeight);
		day1 = (TextView) view.findViewById(R.id.day1);
		day2 = (TextView) view.findViewById(R.id.day2);
		day3 = (TextView) view.findViewById(R.id.day3);
		day4 = (TextView) view.findViewById(R.id.day4);

		wea1 = (TextView) view.findViewById(R.id.weather1);
		wea2 = (TextView) view.findViewById(R.id.weather2);
		wea3 = (TextView) view.findViewById(R.id.weather3);
		wea4 = (TextView) view.findViewById(R.id.weather4);

		mTime1 = (TextView) view.findViewById(R.id.tv_time1);
		mTime2 = (TextView) view.findViewById(R.id.tv_time2);
		mTime3 = (TextView) view.findViewById(R.id.tv_time3);
		mTime4 = (TextView) view.findViewById(R.id.tv_time4);

		btn_refersh.setOnClickListener(this);
		mHome.setOnClickListener(this);
		voice_speak.setOnClickListener(this);
		return view;
	}

	@Override
	public void initData()
	{

		currentCity = CacheUtiles.getString(mContext, CacheUtiles.CURRENTCITY);
		if (TextUtils.isEmpty(currentCity))
		{
			// 如果定位失败
			// 默认北京
			currentCity = "北京市";
			Toast.makeText(mContext, "获取当前城市失败,请重新定位或手动设置... ", 0).show();
		}
		// 设置背景
		// mWeather_bg
		int timeType = IconUtils.getTimeType();
		mWeather_bg.setBackgroundResource(timeType == 0 ? R.drawable.weather_sun_bg : R.drawable.star_bg);
		mRtHead_bg.setBackgroundResource(timeType == 0 ? R.drawable.ic_weather_sunshine_bg : R.drawable.weather_header_bg);
		mCity.setText(currentCity);
		mCity.setTextColor(timeType == 0 ? mContext.getResources().getColor(R.color.mCity_light) : mContext.getResources().getColor(R.color.mCity_night));
		if(timeType==1){
			mWear_cloth.setTextColor(mContext.getResources().getColor(R.color.mCity_night));
			mClean_car.setTextColor(mContext.getResources().getColor(R.color.mCity_night));	
		}
		String string = CacheUtiles.getString(mContext, CacheUtiles.WEATHER_DATAS);
		processData(string);
		// 从网络请求获取数据
		HttpUtils utils = new HttpUtils();
		pb_refersh.setVisibility(View.VISIBLE);
		city_str = currentCity;
		String path = "http://api.map.baidu.com/telematics/v3/weather?location="
						+ city_str + "&output=json&ak=GuZriL3rkm1MUnyTyfsNGvTC";

		System.out.println("path" + path);
		utils.send(HttpMethod.GET, path, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				
				// 请求成功
				String result = responseInfo.result;
				// 缓存数据
				CacheUtiles.setString(mContext, CacheUtiles.WEATHER_DATAS, result);
				System.out.println("设置缓存数据");
				// 数据的处理
				processData(result);
				//影藏进度条
				if(pb_refersh.isShown()){
					pb_refersh.setVisibility(View.GONE);
				}
			}

			@Override
			public void onFailure(HttpException error, String msg)
			{
				// 请求失败
				// System.out.println("请求失败"+msg);
				Toast.makeText(mContext, "更新数据失败,请检查网络连接", 0).show();
				//影藏进度条
				if(pb_refersh.isShown()){
					pb_refersh.setVisibility(View.GONE);
				}
			}
		});

		super.initData();
	}

	/*
	 * private Handler handler = new Handler() { public void
	 * handleMessage(android.os.Message msg) { switch (msg.what) { case 0: //
	 * 设置今天的Date mDate.setText(""); break;
	 * 
	 * default: break; }
	 * 
	 * }; };
	 */
private String getSpeakString(){
	String string = CacheUtiles.getString(mContext, CacheUtiles.WEATHER_DATAS);
	
	Gson gson = new Gson();
	WeatherContextBean bean = gson.fromJson(string, WeatherContextBean.class);
	if(bean==null){
		return "目前没有准确数据";
	}else{
		int top=0;
		int low=0;
	
		List<Info> results = bean.results;
		Info info = results.get(0);// 解析到主要信息
		String currentCity = info.currentCity;
		
		List<Weather> weather_data = info.weather_data;
		Weather weather1= weather_data.get(0);
		//今天的天气描述  温度
		String temperature1 = weather1.temperature;
		int index = TemperatureUtils.getIndex(temperature1);
		if (index > 0)
		{
			int[] temperatures = TemperatureUtils.getTemperature(temperature1);
			top=temperatures[0];
			low=temperatures[1];
			temperature1=low+"到"+top+"摄氏度";
		}
		
		String des1 = weather1.weather;
		//明天的天气描述 温度 
		Weather weather2= weather_data.get(1);
		String temperature2 = weather2.temperature;
		String des2 = weather2.weather;
		int[] temperatures2 = TemperatureUtils.getTemperature(temperature2);
		
		String result=currentCity+" 今天   "+des1+"  气温为  "+temperature1+"              "+"预计明天"+"  "
		+des2+" 气温为 "+temperatures2[1]+"到"+temperatures2[0]+"摄氏度  ";
		return result;
	}
	
}
	private void processData(String json)
	{
		Gson gson = new Gson();
		WeatherContextBean bean = gson.fromJson(json, WeatherContextBean.class);
		// 数据都在bean中了
		// 判断状态
		if (bean == null)
		{
			System.out.println("bean为null");
		}
		else
		{
			if (bean.status.equalsIgnoreCase("success") && bean.error == 0)
			{
				// 如果进来,那么请求失败
				// 到了处理数据的时刻了
				// 给内容实体添加数据
				/*
				 * mWeather_bg; // 整个天气界面的背景 mHome; // 点击进入设置位置 mCity; // 当前的城市名
				 * btn_refersh; // 当前的城市名 mDate; // 当前日期 mRefersh_time; // 刷新时间
				 * mCurrentweather_icon; // 天气的图标 mWeather_state; // 今天天气的状态
				 * tvcurrent_T; // 今天的温度 private TextView mWind_set;//风力 private
				 * TextView pm_set;//PM private TextView weather_des;//天气描述
				 */
				mDate.setText(bean.date);

				// 获得实时的温度
				List<Info> results = bean.results;
				Info info = results.get(0);// 解析到主要信息
				String currentCity2 = info.currentCity;

				List<Wear> index = info.index;
				if(index.size()==0){
					
					mWear_cloth.setText("穿衣指数: " + "暂无数据");
					
					mClean_car.setText("洗车指数: " + "暂无数据");
					weather_des.setText("暂无数据");
					
				}else{
					// 从index中获取穿衣指数和洗车指数
					String cloth_des = index.get(0).des;
					String clean_des = index.get(1).des;
					// mWear_cloth;//穿衣指数
					mWear_cloth.setText("穿衣指数: " + cloth_des);
					// mClean_car;//洗车指数
					mClean_car.setText("洗车指数: " + clean_des);
					// 设置天气描述
					Wear wear = index.get(0);
					String zs = wear.zs;
					weather_des.setText(zs);
					
				}
				List<Weather> weather_data = info.weather_data;
				/*
				 * System.out.println("currentCity2"+currentCity2+"pm25"+pm25+
				 * "index" +index.size()+"weather_data"+weather_data.size());
				 */
				Weather weather = weather_data.get(0);
				/*
				 * System.out.println(weather.date+weather.temperature+
				 * weather.wind);
				 */
				// 实时温度
				
				if(TemperatureUtils.getCurrentTemprature(weather.date)!=-1){
					currentWendu=TemperatureUtils.getCurrentTemprature(weather.date);// 现在的温度
				}else{
					int index2 = TemperatureUtils.getIndex(weather.temperature);
					if(index2==-1){
						currentWendu=TemperatureUtils.getTemprature(weather.temperature);
					}else{
						currentWendu=TemperatureUtils.getTemperature(weather.temperature)[0];
					}
				}
			//int currentWendu = TemperatureUtils.getCurrentTemprature(weather.temperature);// 现在的温度
			
				/*
				 * String[] split = currentWendu.split("："); String[] split2 =
				 * split[1].split(")");
				 */
				tvcurrent_T.setText(currentWendu + "℃");
				String strIcon = weather.weather;
				// 设置天气情况

				mWeather_state.setText(strIcon);
				if(strIcon.indexOf("转")>0){		
					int changeTemp = strIcon.indexOf("转");
					String temp1 = strIcon.substring( 0 ,changeTemp).trim();
					int parseIcon = IconUtils.parseIcon(temp1);

					// 设置图标
					mCurrentweather_icon.setImageResource(parseIcon);
				}else{
					int parseIcon = IconUtils.parseIcon(strIcon);

					// 设置图标
					mCurrentweather_icon.setImageResource(parseIcon);
				}
				
				// 设置风力
				String wind = weather.wind;
				mWind_set.setText(wind);
				// 设置PM
				int pm25 = info.pm25;
				pm_set.setText("PM 2.5: " + pm25);
				// 设置更新时间
				mRefersh_time.setText(IconUtils.getCurTime() + "(更新)");
				setPicDatas(weather_data);

				day1.setText("今天");
				day2.setText(date.get(1));
				day3.setText(date.get(2));
				day4.setText(date.get(3));// 星期几

				wea1.setText(listWeather.get(0));
				wea2.setText(listWeather.get(1));
				wea3.setText(listWeather.get(2));
				wea4.setText(listWeather.get(3));
				
				//设置曲线图的日期
				
				List<String> picDate = DateUtils.getPicDate();
				mTime1.setText(picDate.get(0));
				mTime2.setText(picDate.get(1));
				mTime3.setText(picDate.get(2));
				mTime4.setText(picDate.get(3));
			}
			else
			{
				Toast.makeText(mContext, "获取数据失败", 0).show();
			}
		}
	}

	@Override
	public void onClick(View v)
	{
		int id = v.getId();
		switch (id)
		{
			case R.id.iv_home:
		//进入选择页面
				Intent intent = new Intent(mContext,SelectotCity.class);
				mContext.startActivity(intent);
				break;
	case R.id.btn_refersh:
		// 直接初始化数据就行了
				pb_refersh.setVisibility(View.VISIBLE);
			
				initData();
				break;
	case R.id.voice_speak:
		//开始语言
		String speakString = getSpeakString();
			startSpeak(speakString,"");
				break;
			default:
				break;
		}
		
	}

	private void startSpeak(String String ,String Name)
	{
		SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(mContext, null);
		// 2.合成参数设置，详见《科大讯飞MSC API手册(Android)》 SpeechSynthesizer 类
		mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");// 设置发音人
		mTts.setParameter(SpeechConstant.SPEED, "20");// 设置语速
		mTts.setParameter(SpeechConstant.VOLUME, "80");// 设置音量，范围 0~100
		mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); // 设置云端
		// 设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
		// 保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
		// 如果不需要保存合成音频，注释该行代码
		mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
		// 3.开始合成
		mTts.startSpeaking(String, new MySynthesizerListener());
		
	}
	class MySynthesizerListener implements SynthesizerListener
	{
		// 会话结束回调接口，没有错误时， error为null
		public void onCompleted(SpeechError error)
		{
		}

		// 缓冲进度回调
		// percent为缓冲进度0~100， beginPos为缓冲音频在文本中开始位置， endPos表示缓冲音频在
		// 文本中结束位置， info为附加信息。
		public void onBufferProgress(int percent, int beginPos, int endPos, String info)
		{
		}

		// 开始播放
		public void onSpeakBegin()
		{
		}

		// 暂停播放
		public void onSpeakPaused()
		{
		}

		// 播放进度回调
		// percent为播放进度0~100,beginPos为播放音频在文本中开始位置， endPos表示播放音频在文
		// 本中结束位置.
		public void onSpeakProgress(int percent, int beginPos, int endPos)
		{
		}

		// 恢复播放回调接口
		public void onSpeakResumed()
		{
		}

		// 会话事件回调接口
		public void onEvent(int arg0, int arg1, int arg2, Bundle arg3)
		{
		}
	}

	private void setPicDatas(List<Weather> weather_data)
	{
		Constants constants = new Constants(mContext);
		date = new ArrayList<String>();
		for (int i = 0; i < weather_data.size(); i++)
		{
			String string = weather_data.get(i).date;
			date.add(string);
		}

		listWeather = new ArrayList<String>();
		for (int i = 0; i < weather_data.size(); i++)
		{
			String string = weather_data.get(i).weather;
			listWeather.add(string);
		}
		// setTemperature(List<Integer> top, List<Integer> low)
		top = new ArrayList<Integer>();

		low = new ArrayList<Integer>();
		for (int i = 0; i < weather_data.size(); i++)
		{
			switch (i)
			{
				case 0:
					Weather weather = weather_data.get(i);
					String temperature = weather.temperature;
					int index = TemperatureUtils.getIndex(temperature);
					if (index > 0)
					{
						int[] temperatures = TemperatureUtils.getTemperature(temperature);
						top.add(temperatures[0]);
						low.add(temperatures[1]);
					}
					else
					{
						int temprature = TemperatureUtils.getTemprature(temperature);
						//int currentWendu = TemperatureUtils.getCurrentTemprature(weather.date);
						if (temprature > currentWendu)
						{
							top.add(temprature);
							low.add(currentWendu);
						}
						else
						{ 
							top.add(currentWendu);
							low.add(temprature);
						}
						
					}
					break;
				case 1:

					Weather weather1 = weather_data.get(i);
					String temperature1 = weather1.temperature;

					int[] temperatures = TemperatureUtils.getTemperature(temperature1);
					top.add(temperatures[0]);
					low.add(temperatures[1]);
					break;
				case 2:
					Weather weather2 = weather_data.get(i);
					String temperature2 = weather2.temperature;

					int[] temperatures2 = TemperatureUtils.getTemperature(temperature2);
					top.add(temperatures2[0]);
					low.add(temperatures2[1]);
					break;
				case 3:
					Weather weather3 = weather_data.get(i);
					String temperature3 = weather3.temperature;
					
					int[] temperatures3 = TemperatureUtils.getTemperature(temperature3);
					top.add(temperatures3[0]);
					low.add(temperatures3[1]);
					break;
				default:
					break;
			}
		}

		topPic = new ArrayList<Integer>();

		lowPic = new ArrayList<Integer>();
		for (int i = 0; i < weather_data.size(); i++)
		{
			String string = weather_data.get(i).weather;
			if(string.indexOf("转")>0){		
				int changeTemp = string.indexOf("转");
				String temp1 = string.substring( 0 ,changeTemp).trim();
				String temp2 = string.substring( changeTemp+1 ,string.length()).trim();
				int rid1 = IconUtils.getRid(temp1,0);
				int rid2 = IconUtils.getRid(temp2,1);
				topPic.add(i, rid1);
				lowPic.add(i, rid2);
				
			}else{
				int rid1 = IconUtils.getRid(string,0);
				int rid2 = IconUtils.getRid(string,1);
				topPic.add(i, rid1);
				lowPic.add(i, rid2);
			}
			
		}

		mTrendView.setTemperature(top, low);
		mTrendView.setBitmap(topPic, lowPic);

	}
	/*
	 * //parseDatas("http://m.weather.com.cn/mweather/101280601.shtml");
	 * 解析HTML页面获取数据,是在不是我的菜 private String parseDatas(final String url){ new
	 * Thread(){ public void run() { try { Document doc =
	 * Jsoup.connect(url).timeout(60000).get(); Elements time =
	 * doc.getElementsByClass("sk"); if(time==null){ return; } if(time!=null){
	 * Element dates = time.get(0); if(dates!=null){ Elements h2s =
	 * dates.getElementsByTag("h2"); if(h2s!=null){ Element h2 = h2s.get(0);
	 * 
	 * if(h2!=null){
	 * 
	 * System.out.println(h2.text()+"当前日期"); date+=h2.text(); Message msg =
	 * Message.obtain(); msg.what=DATE; handler.sendMessage(msg);
	 * 
	 * 不知道什么情况,节点数据获取不到 Elements spans = h2.getElementsByTag("span");
	 * if(spans!=null){ Element span = spans.get(0);
	 * 
	 * System.out.println(span.text()+"span数据"); } } } } }
	 * 
	 * Elements elements = doc.getElementsByClass("days7"); Element element =
	 * elements.get(0); if(element!=null){ System.out.println("解析到ul了");
	 * Elements uls = element.getElementsByTag("ul"); //
	 * System.out.println("uls的个数"+uls.size()); Element ul = uls.get(0);
	 * if(ul!=null){ // System.out.println("继续解析数据"); //解析到li节点了 Elements lis =
	 * ul.getElementsByTag("li"); if(lis!=null){ for(Element li:lis){ //循环遍历
	 * String date = li.getElementsByTag("b").text();//获得每天的天气
	 * System.out.print(date+"   "); //进入i节点 Element i =
	 * li.getElementsByTag("i").get(0); //继续深入img节点 Elements imgs =
	 * i.getElementsByTag("img"); for(Element img:imgs ){
	 * 
	 * String string = img.attr("alt"); System.out.print(string+"  ");
	 * 
	 * } String wendu = li.getElementsByTag("span").text();
	 * 
	 * System.out.println(wendu); } } }
	 * 
	 * } } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * };}.start(); return ""; }
	 */
}
