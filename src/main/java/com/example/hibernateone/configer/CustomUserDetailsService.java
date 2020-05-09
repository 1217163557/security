package com.example.hibernateone.configer;


import com.example.hibernateone.dao.UsersRepository;
import com.example.hibernateone.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;


@Component("userDetailsService")
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
   private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        // 1. 查询用户
        Users userFromDatabase = userRepository.findByName(login);
        System.out.println(userFromDatabase);
        if (userFromDatabase == null) {
            //log.warn("User: {} not found", login);
            throw new UsernameNotFoundException("User " + login + " was not found in db");
            //这里找不到必须抛异常
        }
        // 2. 设置角色
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userFromDatabase.getRole());
        grantedAuthorities.add(grantedAuthority);
        return new User(login, userFromDatabase.getPassword(), grantedAuthorities);
    }
}
