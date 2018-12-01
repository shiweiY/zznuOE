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
			
			String ip = getIpAddress(request);
			
			map.clear();
			map.put("user", user);
			map.put("loginId", DateUtil.getCurrentTimeMillis());
			map.put("createTime", DateUtil.getSqlTime());
			map.put("clientIp", ip);

//			session.insert("LoginMapper.insertLoginLog", map);
			login.insertLoginLog(map);
			
			String groupId = user.getGroup();
			String menuId = login.getMenuId(groupId);//菜单id
			
			if(menuId != null && menuId.length() > 1) {
				List<String> mid = Arrays.asList(menuId.split(","));
				List<Menu> menu = login.getUserMenuById(mid);
				
				for (Menu mn : menu) {//获取子菜单
					if(mn.getMenu_child() != null && mn.getMenu_child().length() > 0){
						List<String> mcid = Arrays.asList(mn.getMenu_child().split(","));
						List<Menu> mc = login.getUserMenuById(mcid);
						RedisHelper.setSerialData(mn.getM_id(), mc);
					}
				}
				
				RedisHelper.setSerialData("usrmenu", menu);
			}
			
			
			jp.setFlag(true);
			jp.setPageUrl("index");
			
			RedisHelper.setUserInfo(user);
			
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
