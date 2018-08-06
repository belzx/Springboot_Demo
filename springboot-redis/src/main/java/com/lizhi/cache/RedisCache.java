package com.lizhi.cache;

import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.lizhi.util.JedisUtil;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;
@Component
public class RedisCache<k,v> implements Cache<k,v> {

    @Resource
    private JedisUtil jedisUtil;

    private final String CACHE_PREFIX = "huawei-cache:";

    private byte[] getKey(k k){
        if(k instanceof String){
            return (CACHE_PREFIX+k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    @Override
    public v get(k key) throws CacheException {
        System.out.println("从redis获取权限数据");
        byte[] value = jedisUtil.get(getKey(key));
        if (value != null){
            return (v)SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public v put(k key, v value) throws CacheException {
        byte[] key1 = getKey(key);
        byte[] value1 = SerializationUtils.serialize(value);
        jedisUtil.set(key1,value1);
        jedisUtil.expire(key1,600);
        return value;
    }

    @Override
    public v remove(k key) throws CacheException {
        byte[] key1 = getKey(key);
        byte[] value1 = jedisUtil.get(key1);
        jedisUtil.del(key1);
        if(value1 != null){
            return (v) SerializationUtils.deserialize(value1);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<k> keys() {
        return null;
    }

    @Override
    public Collection<v> values() {
        return null;
    }
}
