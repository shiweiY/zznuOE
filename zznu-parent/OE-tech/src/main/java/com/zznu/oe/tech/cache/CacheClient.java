package com.zznu.oe.tech.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.zznu.oe.model.User;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CacheClient implements CacheManager {
	Logger log = Logger.getLogger(CacheClient.class);
	private JedisPool jedisPool;
	private boolean flag = false;

	public CacheClient(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public String setString(String key, String value) {
		Jedis jedis = getJedis();

		if(!key.isEmpty()){
			try {
				jedis.set(key, value);
				flag = true;
			}finally {
				if(flag) {
					jedis.close();
					flag = false;
				}
			}

		}

		return null;
	}

	public String getString(String key) {
		String value= "";
		if(!key.isEmpty()) {
			Jedis jedis = this.getJedis();
			try {
				value = jedis.get(key);
				flag = true;
			} finally {
				if(flag) {
					jedis.close();
					flag = false;
				}
			}
		}
		return value;
	}


	public User getUserInfo() {
		User user;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			byte[] bts = jedis.get("userInfo".getBytes());

			if(bts != null && bts.length > 0){
				user = (User) unserialize(bts);
				flag = true;
			}else{
				return null;
			}

		} finally {
			if(flag){
				jedis.close();
				flag = false;
			}
		}
		return user;
	}

	public void setUser(User user) {
		Jedis jedis = null;
		if(user != null){
			try {
				jedis = getJedis();
				jedis.set("userInfo".getBytes(), serialize(user));

				flag = true;

			} finally {
				if(flag){
					jedis.close();
					flag = false;
				}
			}

		}

	}

	public void setSerialData(String key, Object value) {
		Jedis jedis = null;
		if(!key.isEmpty()) {
			try {
				jedis = this.getJedis();
				jedis.set(key.getBytes(), serialize(value));
				flag = true;
			} finally {
				if(jedis != null) {
					jedis.close();
					flag = false;
				}
			}

		}

	}

	public Object getSerialData(String key) {
		Jedis jedis = null;
		Object obj = null;

		if(!key.isEmpty()) {
			try {
				jedis = this.getJedis();
				byte[] bt = jedis.get(key.getBytes());
				
				obj = unserialize(bt);
				
			} finally {
				if(jedis != null) {
					jedis.close();
					flag = false;
				}
			}
		}
		
		return obj;
	}


































	/***
	 * 从Jedis池中获取一个jedis
	 * @return
	 */
	public Jedis getJedis() {
		return this.jedisPool.getResource();
	}

	/***
	 * Object序列化
	 * @param obj
	 * @return
	 */
	public byte[] serialize(Object obj){
		byte[] bt ;
		if(obj != null){
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				ObjectOutputStream os = new ObjectOutputStream(baos);
				os.writeObject(obj);

				bt = baos.toByteArray();

				return bt;
			} catch (IOException e) {
				e.printStackTrace();
				log.error("serialize对象序列化失败: "+e.getMessage());
			}
		}

		return null;
	}

	/***
	 * 反序列化Object
	 * @param bt
	 * @return
	 */
	public Object unserialize(byte[] bt){
		Object obj;
		if(bt != null && bt.length > 0){
			ByteArrayInputStream bais = new ByteArrayInputStream(bt);
			try {
				ObjectInputStream ois = new ObjectInputStream(bais);
				obj = ois.readObject();

				return obj;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("unserialize对象反序列化失败: "+e.getMessage());
			}
		}

		return null;
	}

}
