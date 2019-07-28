package com.camelkyhn.springtodolistapi.middleware.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails
{
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User byUsername)
    {
        this.username = byUsername.getUsername();
        this.password = byUsername.getPassword();
        this.authorities = getAuthsFromRoles(byUsername);
    }

    private List<GrantedAuthority> getAuthsFromRoles(User user)
    {
        List<GrantedAuthority> auths = new ArrayList<>();
        for(Role role : user.getRoles())
        {
            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        return auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}