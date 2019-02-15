package com.seeds.yk;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Test;

public class ZJZService {
	
	String url ="http://i.youku.com/i/UMzQ3MzA1MzM2/videos?spm=a2hzp.8244740.0.0";
	
	@Test
	public void downLoadPage() {
		for(int i=2;i<14;i++) {
			String link = getPageLink(i);
			System.out.println(link);
			downloadPage(link);
		}
	}
	
	public void downloadPage(String link) {
		try {
			Iterator<Element> ite = Jsoup.connect(link).get()
			.select("div.videos-list").select("div.items").select("div.v").iterator();
			while(ite.hasNext()) {
				Element ele = ite.next();
				ele = ele.select("div.v-link").select("a[href]").first();
				String linkName = ele.attr("title");
				String linkUrl = ele.attr("href");
				System.out.println("======link:"+linkName+",linkUrl:"+linkUrl);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("strState", "init");
				map.put("strLinkName", linkName);
				map.put("strLinkUrl", linkUrl);
				map.put("strMsg", "");
				map.put("strDateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				saveEle(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveEle(Map<String,Object> obj) {
		String sql =" insert into tbZJZ(strState,strLinkName,strLinkUrl,strMsg,strDateTime) values(?,?,?,?,?);";
		if(false) {
			return;
		}
		DBMysql.runner.insert(sql,"init",obj.get("strLinkName"),obj.get("strLinkUrl"),obj.get("strMsg"),obj.get("strDateTime"));
	}
	
	public String getPageLink(int nPage) {
		Map<String,Object> map = new HashMap<>();
		//http://i.youku.com/i/UMzQ3MzA1MzM2/videos?order=1&page=2&last_item=&last_pn=1&last_vid=946564603
		String link = "http://i.youku.com/i/UMzQ3MzA1MzM2/videos?";
		map.put("order", 1);
		map.put("page", nPage);
		map.put("last_item", "");
		map.put("last_pn", nPage-1);
		map.put("last_vid",946564603);
		
		StringBuilder sb = new StringBuilder();
		map.forEach((key,val)->{
			sb.append(key).append("=").append(val).append("&");
		});
		return link+=sb.deleteCharAt(sb.length() -1);
	}
	
}
