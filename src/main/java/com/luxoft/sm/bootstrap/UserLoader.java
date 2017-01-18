package com.luxoft.sm.bootstrap;

import com.luxoft.sm.domain.Operation;
import com.luxoft.sm.domain.Role;
import com.luxoft.sm.domain.User;
import com.luxoft.sm.repository.OperationRepository;
import com.luxoft.sm.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by smukhlaev on 23.12.2016.
 */

@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent>{
    private UserRepository userRepository;
    private OperationRepository operationRepository;

    private final Logger logger = Logger.getLogger(UserLoader.class);

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setOperationRepository(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        User userOne = new User("Simon", "1", Role.USER);
        User userTwo = new User("Elena", "2", Role.USER);

        Operation fakeOperation = new Operation(1L, new Date(), 1L,
                1000F, 2L, 0F, 1F);
        Operation fakeOperation2 = new Operation(1L, new Date(), 2L,
                1000F, 2L, 0F, 1F);
        Operation fakeOperation3 = new Operation(1L, new Date(), 3L,
                1000F, 2L, 0F, 1F);

        operationRepository.save(fakeOperation);
        operationRepository.save(fakeOperation2);
        operationRepository.save(fakeOperation3);

        userRepository.save(userOne);
        logger.info("User " + userOne.getUserName() + " is saved with id " + userOne.getUserId());

        userRepository.save(userTwo);
        logger.info("User " + userTwo.getUserName() + " is saved with id " + userTwo.getUserId());

    }
}