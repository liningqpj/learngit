package com.example.currentweather;

import com.example.currentweather.Utils.CacheUtiles;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.widget.ImageView;

/**
 * @项目名: 智慧北京
 * @包名: com.example.ZhiHuiBeiJing
 * @类名: DetailUI
 * @创建着: 李宁
 * @创建时间: 2015-5-6下午6:45:37
 * @描述: 具体新闻内容的页面
 */
public class DetailUI extends Activity implements OnClickListener
{
	public static final String	KEY_URL			= "url";
	private static final String	KEY_TEXT_SIZE	= "textSize";
	private ImageView			mIvBack;
	private ImageView			detail_iv_share;
	private ImageView			mIvTextSize;
	private WebView				mWebView;
	private int					mSelectedItem	= 2;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		
		// // 去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.detail);
		// 首先是找到控件
		initView();
		// 加载数据
		initData();
	}

	private void initData()
	{
		mSelectedItem = (int) CacheUtiles.getLong(this, KEY_TEXT_SIZE, mSelectedItem);
		setTextSize();
		String url = getIntent().getStringExtra(KEY_URL);
		// 页面显示
		mWebView.loadUrl(url);
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true); // 设置js可见
		settings.setBuiltInZoomControls(true);// 设置放大缩小的控件
		settings.setUseWideViewPort(true);// 双击缩放
		mIvBack.setOnClickListener(this);
		detail_iv_share.setOnClickListener(this);
		mIvTextSize.setOnClickListener(this);

	}

	private void setTextSize()
	{
		TextSize size = null;
		switch (mSelectedItem)
		{
			case 0:
				size = TextSize.LARGEST;
				break;
			case 1:
				size = TextSize.LARGER;
				break;
			case 2:
				size = TextSize.NORMAL;
				break;
			case 3:
				size = TextSize.SMALLER;
				break;
			case 4:
				size = TextSize.SMALLEST;
				break;
			default:
				break;
		}
		

	}

	private void initView()
	{
		mIvBack = (ImageView) findViewById(R.id.detail_iv_back);
		detail_iv_share = (ImageView) findViewById(R.id.detail_iv_share);
		mIvTextSize = (ImageView) findViewById(R.id.detail_iv_textsize);
		mWebView = (WebView) findViewById(R.id.detail_wv);

	}

	@Override
	public void onClick(View v)
	{
		int id = v.getId();
		switch (id)
		{
			case R.id.detail_iv_back:
				finish();// 直接关闭当前页面
				break;
			case R.id.detail_iv_share:

				break;
			case R.id.detail_iv_textsize:
				// 显示对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("设置字体");
				CharSequence[] items = new CharSequence[] {
						"超大号字体",
						"大号字体",
						"正常字体",
						"小号字体",
						"超小号字体"
				};
				builder.setSingleChoiceItems(items, mSelectedItem, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which)
					{

						mSelectedItem = which;

					}
				});
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						// 设置数据缓存
						CacheUtiles.setLong(DetailUI.this, KEY_TEXT_SIZE, mSelectedItem);

						// 设置字体的大小
						setTextSize();
					}
				});

				// 显示
				builder.show();
				break;

			default:
				break;
		}

	}

}
