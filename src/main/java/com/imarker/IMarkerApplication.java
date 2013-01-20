package com.imarker;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import roboguice.application.RoboApplication;

public class IMarkerApplication extends RoboApplication {

	private static IMarkerApplication instance;
	private boolean loggerEnable = true; // TODO set false when releasing
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		instance = this;

        // 初始化Parse服务
        Parse.initialize(this, Constants.PARSE_APP_ID, Constants.PARSE_CLIENT_KEY);
        // 启用匿名用户
        ParseUser.enableAutomaticUser();
        // 启用全局访问的权限
//        ParseACL defaultACL = new ParseACL();
//        defaultACL.setPublicReadAccess(true);
//        ParseACL.setDefaultACL(defaultACL, true);
	}
	
	public boolean isLoggerEnable() {
		return loggerEnable;
	}

	public static IMarkerApplication getInstance() {
		return instance;
	}
	
}
