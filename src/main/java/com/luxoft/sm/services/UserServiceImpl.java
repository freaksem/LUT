package com.luxoft.sm.services;

import com.luxoft.sm.domain.User;
import com.luxoft.sm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by smukhlaev on 23.12.2016.
 */

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findOneByUserName(userName);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll(new Sort("userName"));
    }

}
