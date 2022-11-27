package com.gogym.apiserver.repository;

import com.gogym.apiserver.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT re FROM Reservation re WHERE re.trainerId = :trainerId ORDER BY re.reservationId DESC")
    List<Reservation> getUsersByTrainerId(String trainerId);

    @Query("SELECT re FROM Reservation re WHERE re.trainerId = :trainerId AND re.userPhone =:userPhone ORDER BY re.reservationId DESC")
    List<Reservation> getScheduleByUserPhone(String trainerId, String userPhone);

    @Query("SELECT re FROM Reservation re WHERE re.trainerId = :trainerId AND re.startTime >= :startTime AND re.endTime <= :endTime ORDER BY re.reservationId DESC")
    List<Reservation> getScheduleByTime(String trainerId, LocalDateTime startTime, LocalDateTime endTime);
}
