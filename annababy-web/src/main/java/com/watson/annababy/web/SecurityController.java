package com.watson.annababy.web;

import com.watson.annababy.model.MenuEntity;
import com.watson.annababy.model.aa.RoleEntity;
import com.watson.annababy.model.aa.UserEntity;
import com.watson.annababy.service.aa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class SecurityController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/api/security/user")
    public Object listUser() {
        List<Map<String, Object>> userList = new LinkedList<>();
        for (UserEntity u : userRepository.findAll()) {
            Map<String, Object> userObj = new HashMap<>();
            userObj.put("id", u.getId());
            userObj.put("name", u.getName());
            userObj.put("role_ids", u.getRoles().stream().map(e->e.getId()).collect(Collectors.toList()));
            userList.add(userObj);
        }
        Map<String, Object> rsp = new HashMap<>();
        rsp.put("data", userList);
        return rsp;
    }

    @GetMapping("/api/security/role")
    public Object listRole() {
        List<Map<String, Object>> roleList = new LinkedList<>();
        for(RoleEntity r : roleRepository.findAll()) {
            Map<String, Object> roleObj = new HashMap<>();
            roleObj.put("id", r.getId());
            roleObj.put("name", r.getName());
            roleList.add(roleObj);
        }
        Map<String, Object> rsp = new HashMap<>();
        rsp.put("data", roleList);
        return rsp;
    }

}
