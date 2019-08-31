package com.watson.annababy.web;

import com.watson.annababy.model.MenuEntity;
import com.watson.annababy.model.aa.RoleEntity;
import com.watson.annababy.model.aa.UserEntity;
import com.watson.annababy.model.aa.PermissionEntity;
import com.watson.annababy.service.aa.MenuRepository;
import com.watson.annababy.service.aa.UserRepository;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Log
public class MenuController {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/menu")
    public Map<String, Object> getMenu() {

        MenuEntity root = menuRepository.findById(0L).get();

        List<Map<String, Object>> items = new LinkedList<>();
        for(MenuEntity m : root.getSubMenus()) {
            fillMenuTree(items, m);
        }

        Map<String, Object> rsp = new HashMap<>();
        rsp.put("data", items);
        return rsp;
    }

    void fillMenuTree(List<Map<String, Object>> items, MenuEntity m) {
        Map<String, Object> item = new HashMap<>();
        item.put("id", m.getId());
        item.put("name", m.getName());
        item.put("leaf", false);
        item.put("classname", m.getClassName());
        if(m.getParent() != null) {
            item.put("parent_id", m.getParent().getId());
        }
        List<Map<String, Object>> subItems = new LinkedList<>();
        for(MenuEntity subm : m.getSubMenus()) {
            if(isGrantedPermission(subm)) {
                fillMenuTree(subItems, subm);
            }
        }
        item.put("items", subItems);
        items.add(item);
    }

    boolean isGrantedPermission(MenuEntity m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(null == auth) {
            throw new AccessDeniedException("No authenticate info.");
        }

        String userName = auth.getName();
        UserEntity user = userRepository.findByName(userName);

        Set<String> grantedPermissionList = new HashSet<>();

        for(RoleEntity r : user.getRoles()) {
            for (PermissionEntity p : r.getPermissions()) {
                grantedPermissionList.add(p.getName());
            }
        }

        Set<String> requiredPermission = new HashSet<>();
        requiredPermission.add(String.format("%s_R", m.getModule()));
        requiredPermission.add(String.format("%s_W", m.getModule()));

        grantedPermissionList.retainAll(requiredPermission);


        if(grantedPermissionList.isEmpty()) {
            return false;
        }
        return true;
    }

}
