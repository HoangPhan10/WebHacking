package com.example.owasp10_springboot.repository;

import com.example.owasp10_springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsernameAndPassword(String username, String password);
    User findUserBySessionId(String sessionId);
}
