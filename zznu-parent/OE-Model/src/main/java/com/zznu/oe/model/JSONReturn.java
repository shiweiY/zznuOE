package com.zznu.oe.model;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JSONReturn {
	
	private boolean flag;
	
	private ArrayList<String> errorList;
	
	private Map returnObj;
	
	private String message;
	
	private String pageUrl;

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public ArrayList<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(ArrayList<String> errorList) {
		this.errorList = errorList;
	}

	public Object getReturnObj() {
		return returnObj;
	}

	public void setReturnObj(Map returnObj) {
		this.returnObj = returnObj;
	}
	
}
