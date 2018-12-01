package com.zznu.oe.web.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/QueryScore")
public class QueryScoreController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String gotoIndex(HttpServletRequest request) {
		System.out.println();
		System.out.println();
		System.out.println();
		return "QueryScore";
	}
}
