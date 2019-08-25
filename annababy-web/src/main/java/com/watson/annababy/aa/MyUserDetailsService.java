package com.watson.annababy.aa;

import com.watson.annababy.model.UserEntity;
import com.watson.annababy.service.aa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity u = userRepository.findByName(name);
        if(u == null) {
            throw new UsernameNotFoundException(name);
        }

        return new MyUserDetails(u);
    }
}
