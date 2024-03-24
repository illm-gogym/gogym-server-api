package com.gogym.apiserver.repository;

import com.gogym.apiserver.dto.reservation.wrapper.ReservationWrapper;
import com.gogym.apiserver.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT u as user, re as reservation, de as workoutDescriptions FROM Reservation re INNER JOIN User u ON re.userPhone = u.userPhone INNER JOIN WorkoutDescriptions de ON re.reservationId = de.reservationId WHERE re.trainerId = :trainerId ORDER BY re.startTime DESC")
    List<ReservationWrapper> getUsersByTrainerId(String trainerId);

    @Query("SELECT u as user, re as reservation, de as workoutDescriptions FROM Reservation re INNER JOIN User u ON re.userPhone = u.userPhone INNER JOIN WorkoutDescriptions de ON re.reservationId = de.reservationId WHERE re.trainerId = :trainerId AND re.userPhone IN (:userPhone) ORDER BY re.startTime DESC")
    List<ReservationWrapper> getScheduleByUserPhone(String trainerId, List<String> userPhone);

    @Query("SELECT u as user, re as reservation, de as workoutDescriptions FROM Reservation re INNER JOIN User u ON re.userPhone = u.userPhone INNER JOIN WorkoutDescriptions de ON re.reservationId = de.reservationId WHERE re.trainerId = :trainerId AND re.startTime >= :startTime AND re.endTime <= :endTime ORDER BY re.startTime DESC")
    List<ReservationWrapper> getScheduleByTime(String trainerId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT u as user, re as reservation, de as workoutDescriptions FROM Reservation re INNER JOIN User u ON re.userPhone = u.userPhone INNER JOIN WorkoutDescriptions de ON re.reservationId = de.reservationId WHERE re.trainerId = :trainerId AND re.userPhone IN (:userPhone) AND re.startTime >= :startTime AND re.endTime <= :endTime ORDER BY re.startTime DESC")
    List<ReservationWrapper> getScheduleByUserPhoneAndTime(String trainerId, List<String> userPhone, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT u as user, re as reservation, de as workoutDescriptions FROM Reservation re INNER JOIN User u ON re.userPhone = u.userPhone INNER JOIN WorkoutDescriptions de ON re.reservationId = de.reservationId WHERE re.trainerId IN (:trainerId) AND re.startTime >= :startTime AND re.endTime <= :endTime ORDER BY re.startTime DESC")
    List<ReservationWrapper> getScheduleByTrainerIdAndTime(List<String> trainerId, LocalDateTime startTime, LocalDateTime endTime);
}
