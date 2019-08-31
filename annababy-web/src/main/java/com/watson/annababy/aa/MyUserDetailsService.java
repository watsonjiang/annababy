package com.watson.annababy.aa;

import com.watson.annababy.model.aa.PermissionEntity;
import com.watson.annababy.model.aa.RoleEntity;
import com.watson.annababy.model.aa.UserEntity;
import com.watson.annababy.service.aa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        if("watson".equals(name)) {
            return new User("watson", passwordEncoder.encode("wapwap12"), Arrays.asList(new SimpleGrantedAuthority[]{new SimpleGrantedAuthority("ADMIN")}));
        }
        UserEntity u = userRepository.findByName(name);
        if(u == null) {
            throw new UsernameNotFoundException(name);
        }
        return toUser(u);
    }

    User toUser(UserEntity u) {

        Set<String> grantedPerms = new HashSet<>();

        for(RoleEntity r : u.getRoles()) {
            for(PermissionEntity p: r.getPermissions()) {
                grantedPerms.add(p.getName());
            }
        }

        List<SimpleGrantedAuthority> aList = grantedPerms.stream().map(e->new SimpleGrantedAuthority(e)).collect(Collectors.toList());

        User user = new User(u.getName(), u.getPasswd(), aList);
        return user;
    }
}
