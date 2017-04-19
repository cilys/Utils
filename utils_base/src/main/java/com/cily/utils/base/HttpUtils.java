package com.cily.utils.base;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtils {
	
	public final static String get(String url) throws IOException{
		URL u = new URL(url);
		URLConnection uc = u.openConnection();
		HttpURLConnection conn = (HttpURLConnection)uc;
		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		conn.connect();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
		
		StringBuilder su = StrUtils.getStringBuilder();
		String lines;
		while ((lines = reader.readLine()) != null){
			su.append(lines);
		}
		reader.close();
		conn.disconnect();
		
		return su.toString();
	}
	
	public final static String post(String url, boolean useCache, Map<String, String> param) throws IOException{
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection)u.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setUseCaches(useCache);
		conn.setInstanceFollowRedirects(true);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		
		conn.connect();
		
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		if(param != null){
			StringBuilder su = StrUtils.getStringBuilder();
			for(String key : param.keySet()){
				String v = param.get(key);
				if(StrUtils.isEmpty(key) || StrUtils.isEmpty(v)){
					
				}else{
					su.append(key);
					su.append("=");
					su.append(URLEncoder.encode(v, "UTF-8"));
					su.append("&");
				}
			}
			String content = su.toString();
			if(content.length() > 1 && content.endsWith("&")){
				content = content.substring(0, content.length() - 2);
			}
			out.writeBytes(content);
		}
		out.flush();
		out.close();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		StringBuilder sbu = StrUtils.getStringBuilder();
		String line;
		while((line = br.readLine()) != null){
			sbu.append(line);
		}
		br.close();
		conn.disconnect();
		
		return sbu.toString();
	}
}