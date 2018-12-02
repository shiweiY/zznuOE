package com.zznu.oe.web.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/exam")
public class ExamController {

	@RequestMapping(method = RequestMethod.GET)
	public String gotoIndex(HttpServletRequest request) {
		
		System.out.println(request.getSession().getId());
		
		return "exam";
	}
}
