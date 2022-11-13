package com.gogym.apiserver.repository;

import com.gogym.apiserver.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, String> {
    Optional<Trainer> findByTrainerId(String trainerId);
}
