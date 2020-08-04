package com.example.demo666.repository;

import com.example.demo666.model.ZipCode;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ZipCodeRepository extends JpaRepository<ZipCode, Long> {

    public ZipCode findByZip(String zipcode);
    
}