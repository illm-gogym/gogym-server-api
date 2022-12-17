package com.gogym.apiserver.repository;

import com.gogym.apiserver.dto.reservation.wrapper.ReservationWrapper;
import com.gogym.apiserver.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, String> {
    Optional<Trainer> findByTrainerId(String trainerId);

    @Query("SELECT trainer FROM Trainer trainer WHERE trainer.gymId IN (SELECT t.gymId FROM Trainer t WHERE t.trainerId = :trainerId) ORDER BY trainer.name")
    List<Trainer> getTrainersByTrainerId(String trainerId);
}
