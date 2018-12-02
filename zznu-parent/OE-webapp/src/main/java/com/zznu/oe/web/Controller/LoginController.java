package com.zznu.oe.web.Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zznu.oe.Dao.Mapper.LoginMapper;
import com.zznu.oe.Util.DateUtil;
import com.zznu.oe.model.JSONReturn;
import com.zznu.oe.model.Menu;
import com.zznu.oe.model.User;
import com.zznu.oe.tech.cache.RedisHelper;


@Controller
@RequestMapping(value = {"","/Login"})
public class LoginController {
	Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	SqlSession session;

	@RequestMapping(method = RequestMethod.GET)
	public String gotoIndex(HttpServletRequest request) {

		return "Login";
	}


	@ResponseBody
	@RequestMapping(value="/LoginSys",method = RequestMethod.POST)
	public JSONReturn Login (HttpServletRequest request){
		
		Map<String,Object> map = new HashMap<String, Object>();
		String username = request.getParameter("uname");
		String password = request.getParameter("pword");

		map.put("username", username);
		map.put("password", password);
		
		
//		User user = session.selectOne("LoginMapper.selectUserForLogin", map);
		LoginMapper login = session.getMapper(LoginMapper.class);
		User user = login.getUserForLogin(map);
		
		JSONReturn jp = new JSONReturn();
		if(user != null) {
			
			String sessionID = request.getSession().getId();
			user.setSessionID(sessionID);
			
			map.clear();
			map.put("user", user);
			map.put("loginId", DateUtil.getCurrentTimeMillis());
			map.put("loginTime", DateUtil.getSqlTime());
			
			String ip = getIpAddress(request);
			map.put("loginIp", ip);

//			session.insert("LoginMapper.insertLoginLog", map);
			login.insertLoginLog(map);//对此次登录记录于loginrecord表中
			
			String groupId = user.getGroup();//获取用户所在权限群组
			String menuId = login.getMenuId(groupId);//获取菜单id  1,2,3
			
			if(menuId != null && menuId.length() > 1) {
				List<String> mid = Arrays.asList(menuId.split(","));
				List<Menu> menu = login.getUserMenuById(mid);//获取菜单
				RedisHelper.setSerialData("usrmenu", menu);//普通菜单放入缓存
				
				//下拉式菜单的子菜单进行遍历获取，然后放入缓存
				for (Menu selectmenu : menu) {
					//一个用户的菜单组中可能有多个下拉式的菜单
					if(selectmenu.getMenu_child() != null && selectmenu.getMenu_child().length() > 0){
						List<String> mcid = Arrays.asList(selectmenu.getMenu_child().split(","));//拆分子菜单为list
						List<Menu> mc = login.getUserMenuById(mcid);//获取子菜单
						RedisHelper.setSerialData("select_menu_"+selectmenu.getM_id(), mc);
					}
				}
				
			}
			
			
			jp.setFlag(true);
			jp.setPageUrl("index");//前往主页
			
			RedisHelper.setUserInfo(user);
			
			RedisHelper.setSerialData("user", user);
			
			return jp;
			
		}else {
			jp.setFlag(false);
			jp.setMessage("用户名或密码错误！");
		}

		return jp;
	}

	public static String getIpAddress(HttpServletRequest request) {  
		String ip = request.getHeader("x-forwarded-for");  
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("Proxy-Client-IP");  
		}  
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("WL-Proxy-Client-IP");  
		}  
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("HTTP_CLIENT_IP");  
		}  
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
		}  
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getRemoteAddr();  
		}  
		return ip;  
	} 

}
