package com.gogym.apiserver.repository;

import com.gogym.apiserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserPhone(String phoneNumber);
}
