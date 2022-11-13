package com.gogym.apiserver.repository;

import com.gogym.apiserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserPhone(String phoneNumber);

    @Query("SELECT u FROM User u WHERE u.userPhone IN (SELECT r.userPhone FROM Registration r WHERE r.trainerId = :trainerId)")
    List<User> getUsersByTrainerId(String trainerId);
}
