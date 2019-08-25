package com.watson.annababy.aa;

import com.watson.annababy.model.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class MyUserDetails implements UserDetails {

    String userName;
    String password;

    static Collection<GrantedAuthority> auths = new LinkedList<GrantedAuthority>() {{add(new GrantedAuthority() {
        @Override
        public String getAuthority() {
            return "annababy";
        }
    });}};

    public MyUserDetails(UserEntity user) {
        userName = user.getName();
        password = user.getPasswd();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auths;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
