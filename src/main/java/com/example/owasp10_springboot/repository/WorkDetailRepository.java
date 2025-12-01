package com.example.owasp10_springboot.repository;

import com.example.owasp10_springboot.entity.WorkDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkDetailRepository extends JpaRepository<WorkDetail,Long> {
    List<WorkDetail> findWorkDetailsByWorkId(Long workId);
}
