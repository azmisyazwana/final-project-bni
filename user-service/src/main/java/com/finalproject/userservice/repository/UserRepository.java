package com.finalproject.userservice.repository;

import com.finalproject.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getDistinctTopByUsername(String username);
}