package com.luxoft.sm.services;

import com.luxoft.sm.domain.User;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by smukhlaev on 23.12.2016.
 */
public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByUserName(String userName);

    Collection<User> getAllUsers();

}
