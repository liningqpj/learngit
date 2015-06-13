package com.example.currentweather.Utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @项目名:   CurrentWeather
 * @包名:    com.example.currentweather.Utils
 * @类名:    JsoupUtils
 * @创建着:   李宁
 * @创建时间:  2015-5-12下午2:21:08
 * @描述:    TODO
 */
public class JsoupUtils
{
	//"http://m.weather.com.cn/mweather/101280601.shtml"
	public static String parseDatas(final String url)
	{
		final StringBuffer buffer = new StringBuffer();
		new Thread(){
			public void run() {		
				try
				{
					Document doc = Jsoup.connect(url).timeout(60000).get();
					Elements time = doc.getElementsByClass("sk");
					if(time==null){
						return;
					}
					if(time!=null){
						Element dates = time.get(0);
						if(dates!=null){
							Elements h2s = dates.getElementsByTag("h2");
							if(h2s!=null){
								Element h2 = h2s.get(0);
								
								if(h2!=null){
									buffer.append(h2.text());
									System.out.println(h2.text()+"当前日期");
									/*不知道什么情况,节点数据获取不到
									 * Elements spans = h2.getElementsByTag("span");
									if(spans!=null){
										Element span = spans.get(0);
										
										System.out.println(span.text()+"span数据");
									}*/
								}
							}						
						}
					}
							
					Elements elements = doc.getElementsByClass("days7");
					Element element = elements.get(0);
					if(element!=null){
						System.out.println("解析到ul了");
						Elements uls = element.getElementsByTag("ul");
//						System.out.println("uls的个数"+uls.size());
						Element ul = uls.get(0);
						if(ul!=null){
//							System.out.println("继续解析数据");
							//解析到li节点了
							Elements lis = ul.getElementsByTag("li");
							if(lis!=null){
								for(Element li:lis){
									//循环遍历
									String date = li.getElementsByTag("b").text();//获得每天的天气
									System.out.print(date+"   ");
									//进入i节点
									Element i  = li.getElementsByTag("i").get(0);
								//继续深入img节点
									Elements imgs = i.getElementsByTag("img");
									for(Element img:imgs ){
										
										String string = img.attr("alt");
										System.out.print(string+"  ");
									
									}
									String wendu = li.getElementsByTag("span").text();
									
									System.out.println(wendu);
								}
							}
						}
						
					}				
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		};}.start();
		return buffer.toString();
	}
}
