package com.rainbow.common.util;

import com.rainbow.common.cache.EHCacheUtils;
import com.rainbow.system.domain.SystemUser;
import net.sf.ehcache.CacheManager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 13260 on 2019/9/7.
 */
public class UserUtils {

    public static SystemUser getCurrentUser(CacheManager cacheManager){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        String key = request.getHeader("auth_id");
        if (null == key){
            key =  request.getParameter("AUTH_ID");
        }

        SystemUser user = (SystemUser)EHCacheUtils.getCache(cacheManager,key);

        return user;

    }
}
