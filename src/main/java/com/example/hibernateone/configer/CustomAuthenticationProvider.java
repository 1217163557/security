package com.example.hibernateone.configer;

import com.example.hibernateone.dao.UsersRepository;
import com.example.hibernateone.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UsersRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String inputName = authentication.getName();
        String inputPassword = authentication.getCredentials().toString();






        // userDetails为数据库中查询到的用户信息
        Users userDetails = userRepository.findByName(inputName);

        // 如果是自定义AuthenticationProvider，需要手动密码校验
        if(!userDetails.getPassword().equals(inputPassword)) {
            throw new BadCredentialsException("密码错误");
        }
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userDetails.getRole());
        grantedAuthorities.add(grantedAuthority);
        return new UsernamePasswordAuthenticationToken(inputName, inputPassword, grantedAuthorities);
    }



    @Override
    public boolean supports(Class<?> authentication) {
        // 这里不要忘记，和UsernamePasswordAuthenticationToken比较
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}