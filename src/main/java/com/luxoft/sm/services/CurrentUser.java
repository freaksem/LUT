package com.luxoft.sm.services;

import com.luxoft.sm.domain.Role;
import com.luxoft.sm.domain.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by sm on 08.01.2017.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getUserName(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getUserId();
    }

    public Role getRole() {
        return user.getRole();
    }


}
