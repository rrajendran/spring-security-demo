package com.capella.admin.userservice;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by ramesh on 01/06/2016.
 */
@Service("userDetailsService")
public class UserService implements UserDetailsService {
    private DatabaseUserService databaseUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return databaseUserService.findUser(username);
    }
}
