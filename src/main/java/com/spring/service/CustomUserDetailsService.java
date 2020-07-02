package com.spring.service;

import com.spring.dao.UserDao;
import com.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userDao.findUserByUserName(s);
        org.springframework.security.core.userdetails.User.UserBuilder builder=null;
        if (user.getUsername()!=null){
            builder=withUsername(user.getUsername());
            builder.password(user.getPassword()).roles(user.getRoles());
        }else{
            throw new RuntimeException("not a valid user");
        }
        return builder.build();
    }
}
