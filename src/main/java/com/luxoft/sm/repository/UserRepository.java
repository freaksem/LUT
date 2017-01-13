package com.luxoft.sm.repository;

import com.luxoft.sm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by smukhlaev on 23.12.2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByUserName(String userName);
}
