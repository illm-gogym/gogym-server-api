package com.gogym.apiserver.repository.registration;

import com.gogym.apiserver.dto.registration.wrapper.RegistrationWrapper;
import com.gogym.apiserver.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("SELECT r.registrationId as registration FROM Registration r WHERE r.trainerId = :trainerId AND r.userPhone = :userPhone")
    String getRegistrationIdByTrainerIdAndUserPhone(String trainerId, String userPhone);

    @Query("SELECT r FROM Registration r WHERE r.userPhone = :userPhone")
    List<Registration> getRegistrationByUserPhoneList(String userPhone);

    @Query("SELECT r FROM Registration r WHERE r.userPhone = :userPhone")
    Optional<Registration> getRegistrationByUserPhone(String userPhone);

    @Query("SELECT r FROM Registration r WHERE r.trainerId = :trainerId")
    List<Registration> getRegistrationByTrainerId(String trainerId);

    @Query("SELECT r as registration, u as user FROM Registration r inner join User u on u.userPhone = :userPhone and u.userPhone = r.userPhone and r.trainerId = :trainerId")
    RegistrationWrapper getRegistrationAndDetailByUserPhone(String trainerId, String userPhone);

    @Query("SELECT r as registration, u as user FROM Registration r join User u on u.userPhone = r.userPhone where r.trainerId = :trainerId")
    List<RegistrationWrapper> getRegistrationAndUserByTrainerId(String trainerId);
}
