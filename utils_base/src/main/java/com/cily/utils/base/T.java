package com.cily.utils.base;

import com.cily.utils.base.log.Logs;

import java.io.IOException;

public class T {

	public static void main(String[] args) {
		try {
			Logs.sysOut(HttpUtils.get("https://github.com/cily-code/Test/blob/master/README.md"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
