package com.gogym.apiserver.repository;

import com.gogym.apiserver.dto.gym.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends JpaRepository<Gym, String> {

}
