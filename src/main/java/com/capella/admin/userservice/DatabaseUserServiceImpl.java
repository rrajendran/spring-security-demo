package com.capella.admin.userservice;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by ramesh on 01/06/2016.
 */
@Service("databaseUserService")
public class DatabaseUserServiceImpl implements DatabaseUserService {

    @Override
    public UserDetails findUser(String username) {
        UserDetails userDetails = null;
        if (username.equals("admin")) {
            Collection<? extends GrantedAuthority> authority = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            userDetails = new User(username, "admin", true, true, true, true, authority);

        }
        System.out.println(userDetails);
        return userDetails;
    }
}
