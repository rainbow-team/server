package com.rainbow.system.service;

import com.rainbow.common.service.IService;
import com.rainbow.system.domain.SystemMenu;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/4/30 14:43
 * @Description:
 **/
@CacheConfig(cacheNames = "MenuService")
public interface MenuService  extends IService<SystemMenu> {

    int addSystemMenu(SystemMenu systemMenu);

    List<SystemMenu> findUserPermissions(String userName);

   // Tree<SystemMenu> getSystemMenuTree();

  //  ResponseBo getMenuList(Page page);

   // ResponseBo getMenuById(String id);

/*
    List<SystemMenu> findUserPermissions(String userName);

    List<SystemMenu> findUserSystemMenus(String userName);

    List<SystemMenu> findAllSystemMenus(SystemMenu systemMenu);

    Tree<SystemMenu> getSystemMenuButtonTree();



    Tree<SystemMenu> getUserSystemMenu(String userName);

    SystemMenu findById(Long systemMenuId);

    SystemMenu findByNameAndType(String systemMenuName, String type);

    void addSystemMenu(SystemMenu systemMenu);

    void updateSystemMenu(SystemMenu systemMenu);

    void deleteMeuns(String systemMeunIds);

    @Cacheable(key = "'url_'+ #p0")
    List<Map<String, String>> getAllUrl(String p1);*/
}
