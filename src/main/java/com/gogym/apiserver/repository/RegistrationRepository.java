package com.gogym.apiserver.repository;

import com.gogym.apiserver.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("SELECT r.registrationId as registration FROM Registration r WHERE r.trainerId = :trainerId AND r.userPhone = :userPhone")
    Long getRegistrationIdByTrainerIdAndUserPhone(String trainerId, String userPhone);

    @Query("SELECT r FROM Registration r WHERE r.userPhone = :userPhone")
    List<Registration> getRegistrationByUserPhoneList(String userPhone);

    @Query("SELECT r FROM Registration r WHERE r.userPhone = :userPhone")
    Optional<Registration> getRegistrationByUserPhone(String userPhone);

    @Query("SELECT r FROM Registration r WHERE r.trainerId = :trainerId")
    List<Registration> getRegistrationByTrainerId(String trainerId);
}
