package com.zznu.oe.web.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zznu.oe.tech.cache.RedisHelper;



@Controller
@RequestMapping(value = "/index")
public class indexMapper {
	
	@RequestMapping(method = RequestMethod.GET)
	public String gotoIndex(HttpServletRequest request) {
		
		System.out.println();
		System.out.println(RedisHelper.getString("user_name"));
		System.out.println();
		
		return "index";
	}

}
