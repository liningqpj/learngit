package com.example.currentweather.ui;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.currentweather.R;
import com.example.currentweather.base.TabController;

/**
 * @项目名: CurrentWeather
 * @包名: com.example.currentweather.ui
 * @类名: WeatherContent
 * @创建着: 李宁
 * @创建时间: 2015-5-8下午7:06:15
 * @描述: TODO
 */
public class Setting extends TabController implements OnItemClickListener
{

	 private TextView setting_login; 
	 /*private TextView owner_dress_helper;
	
	 * private TextView owner_good_app; private TextView owner_moji_shop;
	 * private TextView owner_good_share; private TextView owner_setting;
	 * private TextView owner_wiget;
	 */

	private ListView		lv_setting;
	private List<String>	itemName;
	private List<Drawable>	itemPic;

	public Setting(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected View initContentView(Context context)
	{
		View view = View.inflate(mContext, R.layout.activity_setting, null);
		setting_login=(TextView) view.findViewById(R.id.setting_login);
		lv_setting = (ListView) view.findViewById(R.id.lv_setting);
		setting_login.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v)
			{
				Toast.makeText(mContext, "登陆功能暂时不能用", 0).show();
				
			}});
		return view;
	}

	@Override
	public void initData()
	{
		itemName = new ArrayList<String>();
		itemName.add("皮肤小铺");
		itemName.add("穿衣助手");
		itemName.add("发现App");
		itemName.add("商城");
		itemName.add("分享账号");
		itemName.add("设置");

		itemPic = new ArrayList<Drawable>();
		Drawable drawable = mContext.getResources().getDrawable(R.drawable.owner_wiget);
		itemPic.add(drawable);
		itemPic.add(mContext.getResources().getDrawable(R.drawable.owner_dress_helper));
		itemPic.add(mContext.getResources().getDrawable(R.drawable.owner_good_app));
		itemPic.add(mContext.getResources().getDrawable(R.drawable.owner_moji_shop));
		itemPic.add(mContext.getResources().getDrawable(R.drawable.owner_good_share));
		itemPic.add(mContext.getResources().getDrawable(R.drawable.owner_setting));
		lv_setting.setAdapter(new settingAdapter());
		
		lv_setting.setOnItemClickListener(this);
		super.initData();
	}

	private class ViewHolder
	{
		ImageView	iv;
		TextView	tv;
	}

	private class settingAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			if (itemName != null && itemPic != null) { return itemName.size(); }
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
			ViewHolder holder=null;
			if(convertView==null){
				holder=new ViewHolder();
				convertView= View.inflate(mContext, R.layout.setting_lv_item, null);
				
				// 设置tag
				convertView.setTag(holder);
				//初始化tag
				holder.iv=(ImageView) convertView.findViewById(R.id.iv);
				holder.tv=(TextView) convertView.findViewById(R.id.tv);
			}else{
				holder=(ViewHolder) convertView.getTag();
			}
			holder.iv.setImageDrawable(itemPic.get(position));
			holder.tv.setText(itemName.get(position));
			return convertView;
		}

	}
	/*
	 * @Override public void onClick(View v) { switch (v.getId()) { case
	 * R.id.setting_login: Toast.makeText(mContext, "功能尚未实现", 0).show(); break;
	 * case R.id.owner_dress_helper: Toast.makeText(mContext,
	 * "穿衣助手"+"功能暂未实现，敬请期待...", 0).show(); break; case R.id.owner_good_app:
	 * Toast.makeText(mContext, "发现App"+"功能暂未实现，敬请期待...", 0).show(); break; case
	 * R.id.owner_moji_shop: Toast.makeText(mContext, "商城"+"功能暂未实现，敬请期待...",
	 * 0).show(); break; case R.id.owner_good_share: Toast.makeText(mContext,
	 * "分享账号"+"功能暂未实现，敬请期待...", 0).show(); break; case R.id.owner_setting:
	 * Toast.makeText(mContext, "设置"+"功能暂未实现，敬请期待...", 0).show(); break; case
	 * R.id.owner_wiget: Toast.makeText(mContext, "皮肤小铺"+"功能暂未实现，敬请期待...",
	 * 0).show(); break;
	 * 
	 * default: break; }
	 * 
	 * }
	 */

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Toast.makeText(mContext, itemName.get(position)+"功能暂未实现，敬请期待...", 
		               0).show();
		
	}

}
