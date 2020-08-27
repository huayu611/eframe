package com.huayu.eframe.server.redis;

import com.huayu.eframe.server.service.spring.BeanPool;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Leo on 2020/8/22.
 */
public class RedisUtils
{

    private static RedisTemplate<String, Object> getRedisTemplate()
    {
        RedisTemplate<String, Object> redisTemplate = BeanPool.getService(RedisTemplate.class);
        return redisTemplate;
    }

    public static boolean expire(String key, long time)
    {

        RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
        try
        {

            if (time > 0)
            {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static long getExpire(String key)
    {
        RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    public static boolean hasKey(String key)
    {

        try
        {
            RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
            return redisTemplate.hasKey(key);

        }
        catch (Exception e)
        {

            e.printStackTrace();

            return false;

        }
    }


    public static void del(String... key)
    {
        if (key != null && key.length > 0)
        {
            RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
            if (key.length == 1)
            {
                redisTemplate.delete(key[0]);
            }
            else
            {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    public static Object get(String key) {
        RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
        return key == null ? null : redisTemplate.opsForValue().get(key);

    }

    public static boolean set(String key, Object value) {

        try {
            RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean set(String key, Object value, long time) {

        try {

            if (time > 0) {
                RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);

            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
