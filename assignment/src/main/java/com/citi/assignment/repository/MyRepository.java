package com.citi.assignment.repository;

import com.citi.assignment.entity.MyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyRepository extends JpaRepository<MyEntity, String> {
    Optional<MyEntity> findByValue(String input);
}