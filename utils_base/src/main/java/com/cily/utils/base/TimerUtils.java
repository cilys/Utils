package com.cily.utils.base;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerUtils {
	private final static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	
	public final static void task(Runnable run, long initialDelay, long period, TimeUnit unit){
		Logs.sysOut("" + service.isShutdown());
		if(!service.isShutdown()){			
			service.scheduleAtFixedRate(run, initialDelay, period, unit);	
		}
	}
	
	public final static void shutdown(){
		if(!service.isShutdown()){
			service.shutdown();
		}
	}
	
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				
				Logs.sysOut("执行任务");
//				throw new NullPointerException("123");
			}
		};
		
		task(r, 1, 5, TimeUnit.MINUTES);
		
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				Logs.sysOut("执行定时任务");
//			}
//		};
//		
//		Calendar c = Calendar.getInstance();
//		c.set(Calendar.MINUTE, 12);
//		start(task, c, 60000, Calendar.MINUTE, 1);
	}
	
	public final static void start(TimerTask task, Calendar c, long period, int dateType, int inval){
		Date d = c.getTime();
		if(d.before(new Date())){
			d = changeDate(d, dateType, inval);
		}
		
		Timer t = new Timer();
		t.schedule(task, d, period);
	}
	
	public final static Date changeDate(Date d, int dateType, int num){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(dateType, num);
		return c.getTime();
	}
}