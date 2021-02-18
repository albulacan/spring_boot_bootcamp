package com.springboot.bootcamp.service.impl;

import com.springboot.bootcamp.model.User;
import com.springboot.bootcamp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger _logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userService.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username %s not found.", username));
        } else if (!user.isActive()) {
            throw  new BadCredentialsException("User is not active");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), loadAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> loadAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        _logger.info("{}", user.getRoles().toArray());
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }
}
