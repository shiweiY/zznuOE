package com.zznu.oe.tech.Proxy;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AfterProxyer implements AfterReturningAdvice {

	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		System.out.println("after!");
	}

}
