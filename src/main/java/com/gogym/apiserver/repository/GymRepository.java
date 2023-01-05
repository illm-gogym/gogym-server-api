package com.gogym.apiserver.repository;

import com.gogym.apiserver.dto.gym.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GymRepository extends JpaRepository<Gym, String> {
    @Query("select g from Gym g where gym_name = :gymName and gym_tel = :gymTel")
    public Optional<Gym> findByGymNameAndGymTel(@Param("gymName") String gymName, @Param("gymTel")String gymTel);
}
