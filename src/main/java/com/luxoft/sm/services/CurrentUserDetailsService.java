package com.luxoft.sm.services;

import com.luxoft.sm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by sm on 08.01.2017.
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with user name=%s was not found", userName)));
        return new CurrentUser(user);
    }
}
