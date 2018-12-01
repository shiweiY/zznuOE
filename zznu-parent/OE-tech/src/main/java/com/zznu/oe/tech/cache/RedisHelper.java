package com.zznu.oe.tech.cache;

import org.apache.log4j.Logger;

import com.zznu.oe.model.User;

public class RedisHelper extends JedisClient{
	Logger log = Logger.getLogger(RedisHelper.class);

	public static String setString(String key, String value) {
		return cache.setString(key, value);
	}
	public static String getString(String key) {
		return cache.getString(key);
	}

	public static void setUserInfo(User user){
		cache.setUser(user);
	}
	public static User getUserInfo(){
		return cache.getUserInfo();
	}

	
	public static void setSerialData(String key,Object value) {
		cache.setSerialData(key, value);
	}
	
	public static Object getSerialData(String key) {
		return cache.getSerialData(key);
	}



















}
