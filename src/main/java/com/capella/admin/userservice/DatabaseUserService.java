package com.capella.admin.userservice;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by ramesh on 01/06/2016.
 */
public interface DatabaseUserService {
    /**
     * Find user
     *
     * @param username
     */
    UserDetails findUser(String username);
}
