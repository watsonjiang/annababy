package com.watson.annababy.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.watson.annababy.model.aa.PermissionEntity;
import com.watson.annababy.model.aa.RoleEntity;
import com.watson.annababy.model.aa.UserEntity;
import com.watson.annababy.service.aa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    PermissionRepository permRepository;

    @Autowired
    RoleRepository roleRepository;

    @PreAuthorize("hasAuthority('PERM_USER_R')")
    @GetMapping("/api/security/user")
    public ApiResponse listUser(@RequestParam(name="role_id", required = false) Long roleId) {
        List<Map<String, Object>> userList = new LinkedList<>();
        Iterable<UserEntity> users = null;

        if(null == roleId) {
            users = userRepository.findAll();
        }else{
            users = userRepository.findByRoleId(roleId);
        }

        for (UserEntity u : users) {
            Map<String, Object> userObj = new HashMap<>();
            userObj.put("id", u.getId());
            userObj.put("name", u.getName());
            userObj.put("role_ids", u.getRoles().stream().map(e->e.getId()).collect(Collectors.toList()));
            userList.add(userObj);
        }

        return ApiResponse.buildSuccessResp(userList);
    }


    @PreAuthorize("hasAuthority('PERM_USER_W')")
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

    @PreAuthorize("hasAuthority('PERM_USER_W')")
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

    @PreAuthorize("hasAuthority('PERM_USER_W')")
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
            List<Integer> roleIds = (List<Integer>)userObj.get("role_ids");
            List<RoleEntity> roles = new LinkedList<>();
            for(Integer id : roleIds) {
                RoleEntity r = roleRepository.findById(id.longValue()).get();
                roles.add(r);
            }
            user.setRoles(roles);
            userRepository.save(user);
        }else{
            Long id = Long.valueOf((String)userObj.get("id"));
            UserEntity user = userRepository.findById(id).get();
            user.setName((String)userObj.get("name"));
            user.setPasswd((String)userObj.get("password"));
            List<Integer> roleIds = (List<Integer>)userObj.get("role_ids");
            List<RoleEntity> roles = new LinkedList<>();
            for(Integer rid : roleIds) {
                RoleEntity r = roleRepository.findById(rid.longValue()).get();
                roles.add(r);
            }
            user.setRoles(roles);
        }
    }

    @PreAuthorize("hasAuthority('PERM_ROLE_R')")
    @GetMapping("/api/security/role")
    public ApiResponse listRole() {
        List<Map<String, Object>> roleList = new LinkedList<>();
        for(RoleEntity r : roleRepository.findAll()) {
            Map<String, Object> roleObj = new HashMap<>();
            roleObj.put("id", r.getId());
            roleObj.put("name", r.getName());
            roleObj.put("permission_ids", r.getPermissions().stream().map(e->e.getId()).collect(Collectors.toList()));
            roleList.add(roleObj);
        }
        return ApiResponse.buildSuccessResp(roleList);
    }

    @PreAuthorize("hasAuthority('PERM_ROLE_W')")
    @PostMapping("/api/security/role")
    public ApiResponse createRole(@RequestParam MultiValueMap<String, String> formData) {
        ObjectMapper m = new ObjectMapper();
        try {
            List<String> roleJsons = formData.get("data");
            for (String roleJson : roleJsons) {
                Map<String, Object> roleObj = m.readValue(roleJson, new TypeReference<Map<String, Object>>() {});
                createOrUpdateRole(roleObj);
            }
            return ApiResponse.buildSuccessResp(null);
        }catch(Throwable t) {
            return ApiResponse.buildErrorResp("Unexpected error.", t);
        }
    }

    @PreAuthorize("hasAuthority('PERM_ROLE_W')")
    @PutMapping("/api/security/role/{id}")
    public ApiResponse updateRole(@PathVariable String id, @RequestBody MultiValueMap<String, String> formData) {
        ObjectMapper m = new ObjectMapper();
        try{
            List<String> roleJsons = formData.get("data");
            for(String roleJson : roleJsons) {
                Map<String, Object> roleObj = m.readValue(roleJson, new TypeReference<Map<String, Object>>() {});
                createOrUpdateRole(roleObj);
            }
            return ApiResponse.buildSuccessResp(null);
        }catch (Throwable t) {
            return ApiResponse.buildErrorResp("Unexpected error.", t);
        }
    }

    private void createOrUpdateRole(Map<String, Object> roleObj) {
        if(!roleObj.containsKey("id") || "" == roleObj.get("id")) {
            RoleEntity role = new RoleEntity();
            role.setName((String) roleObj.get("name"));
            List<Integer> permIds = (List<Integer>)roleObj.get("permission_ids");
            List<PermissionEntity> perms = new LinkedList<>();
            for(Integer id : permIds) {
                PermissionEntity p = permRepository.findById(id.longValue()).get();
                perms.add(p);
            }
            role.setPermissions(perms);
            roleRepository.save(role);
        }else{
            Long id = Long.valueOf((String)roleObj.get("id"));
            RoleEntity role = roleRepository.findById(id).get();
            role.setName((String)roleObj.get("name"));
            List<Integer> permIds = (List<Integer>)roleObj.get("permission_ids");
            List<PermissionEntity> perms = new LinkedList<>();
            for(Integer pid : permIds) {
                PermissionEntity p = permRepository.findById(pid.longValue()).get();
                perms.add(p);
            }
            role.setPermissions(perms);
        }
    }

    @PreAuthorize("hasAuthority('PERM_ROLE_W')")
    @DeleteMapping("/api/security/role/{id}")
    public ApiResponse deleteRole(@PathVariable Long id) {
        permRepository.deleteById(id);
        return ApiResponse.buildSuccessResp(null);
    }

    @PreAuthorize("hasAuthority('PERM_ROLE_R')")
    @GetMapping("/api/security/permission")
    public ApiResponse listPermission() {
        List<Map<String, Object>> permList = new LinkedList<>();
        for(PermissionEntity p : permRepository.findAll()) {
            Map<String, Object> permObj = new HashMap<>();
            permObj.put("id", p.getId());
            permObj.put("name", p.getName());
            permList.add(permObj);
        }
        return ApiResponse.buildSuccessResp(permList);
    }

}
