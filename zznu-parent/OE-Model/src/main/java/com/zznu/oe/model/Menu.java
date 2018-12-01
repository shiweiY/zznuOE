package com.zznu.oe.model;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("MENU")
public class Menu implements Serializable{
	private static final long serialVersionUID = -7646297526343396852L;
	private String m_id;
	private String menu_type;
	private String menu_page;
	private String menu_name;
	private String menu_parent;	
	private String menu_child;
	
	
	public String getMenu_parent() {
		return menu_parent;
	}
	public void setMenu_parent(String menu_parent) {
		this.menu_parent = menu_parent;
	}
	public String getMenu_child() {
		return menu_child;
	}
	public void setMenu_child(String menu_child) {
		this.menu_child = menu_child;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getMenu_page() {
		return menu_page;
	}
	public void setMenu_page(String menu_page) {
		this.menu_page = menu_page;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	
}
