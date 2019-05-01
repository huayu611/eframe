package com.huayu.eframe.server.cache;

import com.huayu.eframe.server.tool.basic.Null;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Configuration
// 标注启动了缓存
@EnableCaching
@Service("cacheProvider")
public class CacheProvider
{
    @Resource(name = "cacheMachine")
    private Cache cache;

    public Cache getCache()
    {
        return cache;
    }

    public void setCache(Cache cache)
    {
        this.cache = cache;
    }


    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean()
    {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(null);
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

    @Bean(name = "cacheMachine")
    public EhCacheFactoryBean ehCacheFactoryBean()
    {
        EhCacheFactoryBean factory = new EhCacheFactoryBean();
        factory.setCacheManager(ehCacheManagerFactoryBean().getObject());
        factory.setCacheName("cacheMachine");
        return factory;
    }

    public void setElementToCache(String cacheKey, Object obj)
    {
        if (isExistCache(cacheKey))
        {
            cache.remove(cacheKey);
        }
        cache.put(new Element(cacheKey, obj));
    }

    public boolean isExistCache(String cacheKey)
    {
        return Null.isNotNull(cache.get(cacheKey)) && Null.isNotNull(cache.get(cacheKey).getObjectValue()) ? true
                : false;
    }

    public <T> T getElementToCache(String cacheKey)
    {
        if (isExistCache(cacheKey))
        {
            Object obj = cache.get(cacheKey).getObjectValue();
            return (T) obj;
        }
        return null;
    }
}
