package com.example.owasp10_springboot.repository;

import com.example.owasp10_springboot.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work,Long> {
    Work findWorkBySessionId(String sessionId);

}
