package com.luxoft.sm.services;

import com.luxoft.sm.domain.Role;
import org.springframework.stereotype.Service;

/**
 * Created by sm on 08.01.2017.
 */
@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser != null
                && (currentUser.getRole() == Role.USER || currentUser.getId().equals(userId));
    }

}