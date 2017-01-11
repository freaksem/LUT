package com.luxoft.sm.bootstrap;

import com.luxoft.sm.domain.Role;
import com.luxoft.sm.domain.User;
import com.luxoft.sm.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by smukhlaev on 23.12.2016.
 */

@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent>{
    private UserRepository userRepository;

    private Logger logger = Logger.getLogger(UserLoader.class);

    @Value("${amount.ruble}")
    private Long userRubles;
    @Value("${amount.dollar}")
    private Long userDollars;
    @Value("${amount.euro}")
    private Long userEuros;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        User userOne = new User("Simon", "password1", Role.USER, userRubles, userDollars, userEuros);
        User userTwo = new User("Elena", "password2", Role.USER, userRubles, userDollars, userEuros);

        userRepository.save(userOne);
        logger.info("User " + userOne.getUserName() + " is saved with id " + userOne.getUserId());

        userRepository.save(userTwo);
        logger.info("User " + userTwo.getUserName() + " is saved with id " + userTwo.getUserId());

    }
}