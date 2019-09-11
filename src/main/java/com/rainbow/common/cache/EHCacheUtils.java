package com.rainbow.common.cache;

import com.rainbow.system.domain.SystemUser;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 13260 on 2019/9/1.
 */
public class EHCacheUtils {

    /**
     * 设置缓存对象
     * @param cacheManager
     * @param key
     * @param object
     */
    public static void setCache(CacheManager cacheManager, String key, Object object){

        Cache cache = cacheManager.getCache("objectCache");
        Element element = new Element(key,object);
        cache.put(element);
    }

    /**
     * 从缓存中取出对象
     * @param cacheManager
     * @param key
     * @return
     */
    public static Object getCache(CacheManager cacheManager,String key){
        Object object = null;
        Cache cache = cacheManager.getCache("objectCache");
        if(cache.get(key)!=null && !cache.get(key).equals("")){
            object = cache.get(key).getObjectValue();
        }
        return object;

    }

    public static  void deleteCache(CacheManager cacheManager){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        String key = request.getHeader("auth_id");
        if (null == key){
            key =  request.getParameter("AUTH_ID");
        }

        Cache cache = cacheManager.getCache("objectCache");
        if(cache.get(key)!=null && !cache.get(key).equals("")){
            cache.remove(key);
        }
    }

    public static void deleteCacheByUserId(CacheManager cacheManager,String id){
        Cache cache = cacheManager.getCache("objectCache");
        SystemUser object = null;

        List<String> list =  cache.getKeys();
        if(list!=null&&list.size()>0){

            for (int i=0;i<list.size();i++){
                if(cache.get(list.get(i))!=null && !cache.get(list.get(i)).equals("")){
                    object =(SystemUser) cache.get(list.get(i)).getObjectValue();
                    if(object.getId().equals(id)){
                        cache.remove(list.get(i));
                        break;
                    }
                }
            }

        }

    }
}
