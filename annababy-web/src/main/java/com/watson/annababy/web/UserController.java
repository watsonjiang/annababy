package com.watson.annababy.web;

import com.watson.annababy.model.MenuEntity;
import com.watson.annababy.model.UserEntity;
import com.watson.annababy.model.aa.ArtifactEntity;
import com.watson.annababy.model.aa.PermissionEntity;
import com.watson.annababy.model.aa.RoleEntity;
import com.watson.annababy.service.aa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    PermissionRepository permRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ArtifactRepository artifactRepository;

    @GetMapping("/hello")
    public String hello(@RequestParam(value="name", required = false) String name) {

        return String.format("Hello %s", name);
    }

    @GetMapping("/create")
    public String create() {

        UserEntity u = new UserEntity();
        u.setName("watson");

        RoleEntity r = new RoleEntity();
        roleRepository.save(r);

        u.setRole(r);

        MenuEntity m = new MenuEntity();
        m.setText("menu1");

        MenuEntity m1 = new MenuEntity();
        m1.setText("submenu1");

        m1.setParent(m);

        ArtifactEntity a = new ArtifactEntity();
        artifactRepository.save(a);

        m.setArtifact(a);

        PermissionEntity p = new PermissionEntity();
        p.setArtifact(a);
        p.setRole(r);
        p.setPerm("rw");
        permRepository.save(p);

        userRepository.save(u);
        menuRepository.save(m1);
        menuRepository.save(m);

        return "OK";
    }

    @GetMapping("/list")
    public List<String> list() {

        StringBuffer sb = new StringBuffer();
        MenuEntity m = menuRepository.findByText("menu1").get(0);

        return m.getSubMenuList().stream().map(e -> e.getText()).collect(Collectors.toList());
    }


}
