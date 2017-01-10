package com.luxoft.sm.services;

/**
 * Created by sm on 08.01.2017.
 */
public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}