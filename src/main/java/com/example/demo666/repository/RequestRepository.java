package com.example.demo666.repository;

import com.example.demo666.model.Request;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
    
}