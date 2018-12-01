package com.zznu.oe.tech.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClient {
	private static JedisPool jedisPool;
	protected static CacheManager cache;
	
	
	static {
		JedisPoolConfig config = new JedisPoolConfig();
		Properties proper = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("redis.properties");
		
		try {
			proper.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Logger.getLogger(JedisClient.class).debug("redis配置文件读取完成,服务器地址: ---  //"+proper.getProperty("redis.host"));
		
		config.setMaxTotal(Integer.parseInt(proper.getProperty("redis.pool.maxActive")));
		config.setMaxIdle(Integer.parseInt(proper.getProperty("redis.pool.maxIdle")));
		config.setMinIdle(Integer.parseInt(proper.getProperty("redis.pool.minIdle")));
		config.setMaxWaitMillis(Integer.parseInt(proper.getProperty("redis.pool.maxWaitMillis")));
		config.setTestOnBorrow(Boolean.valueOf(proper.getProperty("redis.pool.testOnBorrow")).booleanValue());
		config.setTestOnReturn(Boolean.valueOf(proper.getProperty("redis.pool.testOnReturn")).booleanValue());
	
		jedisPool = new JedisPool(config,proper.getProperty("redis.host"),
								  Integer.valueOf(proper.getProperty("redis.port")).intValue(),
								  0,proper.getProperty("redis.password"));
		
		cache = new CacheClient(jedisPool);
		
		Logger.getLogger(JedisClient.class).debug("JedisClient及JedisPool初始化成功");
		
	}
	
	
}