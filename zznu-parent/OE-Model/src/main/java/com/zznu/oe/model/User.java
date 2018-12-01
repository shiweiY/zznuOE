package com.zznu.oe.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Alias("USER")
@Component
public class User implements Serializable {
	private static final long serialVersionUID = -1825264818746319054L;
	private String id;
	private String username;
	private String password;
	private String name;
	private Integer age;
	private String telephone;
	private String address;
	private String jnumber;
	private String group;
	private String other;
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJnumber() {
		return jnumber;
	}
	public void setJnumber(String jnumber) {
		this.jnumber = jnumber;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
