package com.zznu.oe.Dao.Mapper;

import java.util.List;
import java.util.Map;

import com.zznu.oe.model.User;


public interface LoginMapper {
	//登录返回User
	public User getUserForLogin(Map map);
	//记录登录的信息
	public void insertLoginLog(Map map);
	//获取登录用户的菜单权限
	public String getMenuId(String groupId);
	//获取菜单
	public List getUserMenuById(List<String> mid);
}
