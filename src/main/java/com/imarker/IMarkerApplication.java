package com.imarker;

import com.imarker.constant.ParseConstants;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import roboguice.application.RoboApplication;

public class IMarkerApplication extends RoboApplication {

	private static IMarkerApplication mInstance;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		mInstance = this;

        // 初始化Parse服务
        Parse.initialize(this, ParseConstants.PARSE_APP_ID, ParseConstants.PARSE_CLIENT_KEY);
        // 启用匿名用户
        ParseUser.enableAutomaticUser();
        // 启用全局访问的权限
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
	}

	public static IMarkerApplication getInstance() {
		return mInstance;
	}
	
}
