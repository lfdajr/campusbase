package com.svlada.security.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 4, 2016
 */
public class UserContext {
    private final String username;
    private final String email;
    private final Long id;
    private final List<GrantedAuthority> authorities;

    private UserContext(String username, String email, Long id, List<GrantedAuthority> authorities) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.authorities = authorities;
    }
    
    public static UserContext create(String username, String email, Long id, List<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, email, id, authorities);
    }

    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }
    
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
