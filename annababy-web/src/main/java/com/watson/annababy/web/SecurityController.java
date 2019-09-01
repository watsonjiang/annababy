package com.watson.annababy.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.watson.annababy.model.MenuEntity;
import com.watson.annababy.model.aa.RoleEntity;
import com.watson.annababy.model.aa.UserEntity;
import com.watson.annababy.service.aa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Transactional
public class SecurityController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/api/security/user")
    public ApiResponse listUser() {
        List<Map<String, Object>> userList = new LinkedList<>();
        for (UserEntity u : userRepository.findAll()) {
            Map<String, Object> userObj = new HashMap<>();
            userObj.put("id", u.getId());
            userObj.put("name", u.getName());
            userObj.put("role_ids", u.getRoles().stream().map(e->e.getId()).collect(Collectors.toList()));
            userList.add(userObj);
        }

        return ApiResponse.buildSuccessResp(userList);
    }

    @PostMapping("/api/security/user")
    public ApiResponse createUser(@RequestParam MultiValueMap<String, String> formData) {
        ObjectMapper m = new ObjectMapper();
        try {
            List<String> userJsons = formData.get("data");
            for (String userJson : userJsons) {
                Map<String, Object> userObj = m.readValue(userJson, new TypeReference<Map<String, Object>>() {});
                createOrUpdateUser(userObj);
            }
            return ApiResponse.buildSuccessResp(null);
        }catch(Throwable t) {
            return ApiResponse.buildErrorResp("Unexpected error.", t);
        }
    }

    @PutMapping("/api/security/user/{id}")
    public ApiResponse updateUser(@PathVariable String id, @RequestBody MultiValueMap<String, String> formData) {
        ObjectMapper m = new ObjectMapper();
        try{
            List<String> userJsons = formData.get("data");
            for(String userJson : userJsons) {
                Map<String, Object> userObj = m.readValue(userJson, new TypeReference<Map<String, Object>>() {});
                createOrUpdateUser(userObj);
            }
            return ApiResponse.buildSuccessResp(null);
        }catch (Throwable t) {
            return ApiResponse.buildErrorResp("Unexpected error.", t);
        }
    }

    @DeleteMapping("/api/security/user/{id}")
    public ApiResponse deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ApiResponse.buildSuccessResp(null);
    }

    private void createOrUpdateUser(Map<String, Object> userObj) {
        if(!userObj.containsKey("id") || "" == userObj.get("id")) {
            UserEntity user = new UserEntity();
            user.setName((String) userObj.get("name"));
            user.setPasswd((String) userObj.get("password"));
            userRepository.save(user);
        }else{
            Long id = Long.valueOf((String)userObj.get("id"));
            UserEntity user = userRepository.findById(id).get();
            user.setName((String)userObj.get("name"));
            user.setPasswd((String)userObj.get("password"));

        }
    }

    @GetMapping("/api/security/role")
    public ApiResponse listRole() {
        List<Map<String, Object>> roleList = new LinkedList<>();
        for(RoleEntity r : roleRepository.findAll()) {
            Map<String, Object> roleObj = new HashMap<>();
            roleObj.put("id", r.getId());
            roleObj.put("name", r.getName());
            roleList.add(roleObj);
        }
        return ApiResponse.buildSuccessResp(roleList);
    }

}
