package com.luxoft.sm.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

/**
 * Created by smukhlaev on 23.12.2016.
 */

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long userId;
    @Column(name = "userName", nullable = false, unique = true)
    private String userName;
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    @Column(name = "ruBalance", nullable = false)
    private Long ruBalance;
    @Column(name = "usBalance", nullable = false)
    private Long usBalance;
    @Column(name = "euBalance", nullable = false)
    private Long euBalance;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}

    public User(String userName, String password, Role role, Long ruBalance, Long usBalance, Long euBalance) {
        this.userName = userName;
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
        this.role = role;
        this.ruBalance = ruBalance;
        this.usBalance = usBalance;
        this.euBalance = euBalance;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String password) {
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public Long getRuBalance() {
        return ruBalance;
    }
    public void setRuBalance(Long ruBalance) {
        this.ruBalance = ruBalance;
    }

    public Long getUsBalance() {
        return usBalance;
    }
    public void setUsBalance(Long usBalance) {
        this.usBalance = usBalance;
    }

    public Long getEuBalance() {
        return euBalance;
    }
    public void setEuBalance(Long euBalance) {
        this.euBalance = euBalance;
    }
    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                userId, userName, passwordHash);
    }
}
