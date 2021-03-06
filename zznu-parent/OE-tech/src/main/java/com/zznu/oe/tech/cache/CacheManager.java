package com.zznu.oe.tech.cache;

import com.zznu.oe.model.User;

public interface CacheManager {
	
	//字符串数据
	String setString(String key,String value);
	String getString(String key);
	
	//User
	void setUser(User value);
	User getUserInfo();
	
	//Object
	void setSerialData(String key,Object value);
	Object getSerialData(String key);
	
}
