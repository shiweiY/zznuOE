package com.zznu.oe.tech.Proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MethodAroundProxyer implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		String methodName = invocation.getMethod().getName();
		
		System.out.println("before!");
		Object result = invocation.proceed();
		System.out.println("after!");
		
		
		
		return result;
	}

}
