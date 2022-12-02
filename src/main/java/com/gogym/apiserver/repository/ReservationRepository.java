package com.gogym.apiserver.repository;

import com.gogym.apiserver.dto.reservation.wrapper.ReservationWrapper;
import com.gogym.apiserver.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT u as user, re as reservation FROM Reservation re INNER JOIN User u ON re.userPhone = u.userPhone WHERE re.trainerId = :trainerId ORDER BY re.reservationId DESC")
    List<ReservationWrapper> getUsersByTrainerId(String trainerId);

    @Query("SELECT u as user, re as reservation FROM Reservation re INNER JOIN User u ON re.userPhone = u.userPhone WHERE re.trainerId = :trainerId AND re.userPhone =:userPhone ORDER BY re.reservationId DESC")
    List<ReservationWrapper> getScheduleByUserPhone(String trainerId, String userPhone);

    @Query("SELECT u as user, re as reservation FROM Reservation re INNER JOIN User u ON re.userPhone = u.userPhone WHERE re.trainerId = :trainerId AND re.startTime >= :startTime AND re.endTime <= :endTime ORDER BY re.reservationId DESC")
    List<ReservationWrapper> getScheduleByTime(String trainerId, LocalDateTime startTime, LocalDateTime endTime);
}
