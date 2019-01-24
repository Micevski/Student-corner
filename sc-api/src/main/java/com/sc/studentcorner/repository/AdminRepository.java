package com.sc.studentcorner.repository;

import com.sc.studentcorner.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByEmail(String email);
    Optional<Admin> findByEmail(String username);
}
