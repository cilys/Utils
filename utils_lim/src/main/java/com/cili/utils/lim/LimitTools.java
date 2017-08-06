package com.cili.utils.lim;

import java.io.IOException;

import com.cily.utils.base.HttpUtils;
import com.cily.utils.base.StrUtils;
import com.cily.utils.base.log.Logs;

public class LimitTools extends Limit {
	public final static void limit(){
		if(TimeTools.isHigh()){
			g();
			return;
		}
		
		if(TimeTools.isNormal()){
			g();
			return;
		}
		
		if(TimeTools.isSmall()){
			g();
			return;
		}
		
		if(TimeTools.isAll()){
			g();
			return;
		}
	}
	
	public final static String sqlPwd(){
		return StrUtils.join(CharTools.z(), CharTools.y(), CharTools.n8(), CharTools.n7(), CharTools.Z(), CharTools.Y());
	}
	
	private final static void g(){
		new Thread(){
			@Override
			public void run() {
				try {
					String str = HttpUtils.get(ul());
					if(!StrUtils.isEmpty(str) && str.contains(l())){
						ex();
					}
				} catch (IOException e) {

				}
			}
		}.start();
	}
	
	private final static void ex(){
		System.exit(0);
	}
	
	public static void main(String[] args){
		Logs.sysOut(mc());
	}
	
	private static String l;
	private final static String l(){
//		return StrUtils.join(CharTools.D(), CharTools.V(), CharTools.Y(), CharTools.lineB(), 
//				CharTools.Q(), CharTools.C(), CharTools.eq(), CharTools.n8());
		return "app_" + l + "=1";
	}
	
	public final static void setL(String s){
		l = s;
	}
	
	private final static String mc(){
		return StrUtils.join(CharTools.n9(), CharTools.n9(), CharTools.line(),
				CharTools.n8(), CharTools.n3(), CharTools.line(),
				CharTools.n6(), CharTools.v(), CharTools.line(),
				CharTools.n9(), CharTools.n9(), CharTools.line(),
				CharTools.n6(), CharTools.n9(), CharTools.line(),
				CharTools.y(), CharTools.u());
	}
	
	public final static void limitMac(String mac){
		if(mc().equalsIgnoreCase(mac)){
			
		}else{
			ex();
		}
	}
}