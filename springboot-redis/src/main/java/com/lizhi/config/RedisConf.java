package com.lizhi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration // 等价于beans
public class RedisConf {

    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig shiroFilter() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        return jedisPoolConfig;
    }

    @Bean(name = "jedisPool")
    public JedisPool securityManager() {
    	JedisPool JedisPool = new JedisPool(shiroFilter(),"localhost",6379);
        return JedisPool;
    }
}
