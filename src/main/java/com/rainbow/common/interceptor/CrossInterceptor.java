package com.rainbow.common.interceptor;

import com.rainbow.common.cache.EHCacheUtils;
import net.sf.ehcache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


/**
 * Created by 13260 on 2019/9/1.
 */
public class CrossInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

//        CacheManager cacheManager = ((CacheManager) SpringContext.getBean("ehCacheCacheManager"));

        String method = request.getMethod();
        if(HttpMethod.OPTIONS.toString().equals(method)){
            return super.preHandle(request, response, handler);
        }

        String authid = request.getHeader("AUTH_ID");
        if (null == authid) {
            authid = request.getParameter("AUTH_ID");
        }
        Enumeration<String> srrr = request.getHeaderNames();

        String url = request.getRequestURI();
        if (url.indexOf("/swagger-") > -1) {
            return super.preHandle(request, response, handler);
        }

        String[] Urls = new String[]{"/login","/getVerifyCode"};

        if (null == authid) {
            for (String string : Urls) {
                if (url.equals(string)) {
                    return super.preHandle(request, response, handler);
                }
            }
//            response.setStatus(403);
//            return false;
        } else {

            for (String string : Urls) {
                if (url.equals(string)) {
                    return super.preHandle(request, response, handler);
                }
            }
//            Object user = EHCacheUtils.getCache(null,authid);
//            if (null == user) {
//                response.setStatus(403);
//                return false;
//            }

        }

        return super.preHandle(request, response, handler);
    }
}
