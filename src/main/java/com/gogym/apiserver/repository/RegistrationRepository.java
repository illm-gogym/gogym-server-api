package com.gogym.apiserver.repository;

import com.gogym.apiserver.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

}
