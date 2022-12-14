package com.gogym.apiserver.service;

<<<<<<< HEAD
import com.gogym.apiserver.controller.response.CommonResponse;
=======
import com.gogym.apiserver.dto.reservation.ReservationDto;
>>>>>>> main
import com.gogym.apiserver.dto.reservation.ReservationSaveRequestDto;
import com.gogym.apiserver.dto.reservation.ReservationUpdateRequestDto;
import com.gogym.apiserver.dto.reservation.ReservationViewRequestDto;
import com.gogym.apiserver.dto.reservation.wrapper.ReservationWrapper;
import com.gogym.apiserver.dto.trainer.TrainerSaveRequestDto;
import com.gogym.apiserver.entity.Reservation;
import com.gogym.apiserver.error.common.ErrorCode;
import com.gogym.apiserver.error.common.ErrorResponse;
import com.gogym.apiserver.repository.ReservationRepository;
import com.gogym.apiserver.utils.DateUtil;
import com.gogym.apiserver.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public List<ReservationWrapper> getScheduleByTrainer(String trainerId) {
        return reservationRepository.getUsersByTrainerId(trainerId);
    }

    public List<ReservationWrapper> getScheduleByUserPhone(ReservationViewRequestDto requestDto) {
        return reservationRepository.getScheduleByUserPhone(SecurityUtil.getCurrentTrainerId().get(), requestDto.getUserPhone());
    }

    public List<ReservationWrapper> getScheduleByTime(ReservationViewRequestDto requestDto) {
        return reservationRepository.getScheduleByTime(SecurityUtil.getCurrentTrainerId().get(), requestDto.getStartTime(), requestDto.getEndTime());
    }

    @Transactional
    public List<Reservation> addSchedule(ReservationSaveRequestDto requestDto) {
        return reservationRepository.saveAll(makeReservation(requestDto));
    }


    private List<Reservation> makeReservation(ReservationSaveRequestDto requestDto) {
        List<Reservation> reservations = new ArrayList<>();
        for (ReservationDto reservationDto : requestDto.getReservations()) {

            Reservation reservation = Reservation.builder()
                    .trainerId(SecurityUtil.getCurrentTrainerId().get())
                    .userPhone(reservationDto.getUserPhone())
                    .startTime(reservationDto.getStartTime())
                    .endTime(reservationDto.getEndTime())
                    .description(reservationDto.getDescription())
                    .usageState(reservationDto.getUsageState())
                    .build();

            reservations.add(reservation);
        }
        return reservations;
    }

<<<<<<< HEAD
    public Reservation updateSchedule(ReservationUpdateRequestDto requestDto) {
        Optional<Reservation> byId = reservationRepository.findById(requestDto.getRegistrationId());
        if (!byId.isPresent()) {
            new ErrorResponse(ErrorCode.NOT_FOUND);
        }

        Reservation reservation = byId.get();
        reservation.updateReservation(requestDto.getUserPhone()
                , requestDto.getStartTime()
                , requestDto.getEndTime()
                , requestDto.getDescription()
                , requestDto.getUsageState());

        return reservationRepository.save(reservation);
    }

    public Reservation deleteSchedule(Long id) {
        Optional<Reservation> byId = reservationRepository.findById(id);
        if (!byId.isPresent()) {
            new ErrorResponse(ErrorCode.NOT_FOUND);
        }

        Reservation reservation = byId.get();
        reservationRepository.delete(reservation);

        return reservation;
    }
=======

>>>>>>> main
}
