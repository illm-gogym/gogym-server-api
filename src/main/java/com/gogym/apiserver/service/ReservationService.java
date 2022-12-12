package com.gogym.apiserver.service;

import com.gogym.apiserver.controller.response.CommonResponse;
import com.gogym.apiserver.dto.reservation.ReservationSaveRequestDto;
import com.gogym.apiserver.dto.reservation.ReservationUpdateRequestDto;
import com.gogym.apiserver.dto.reservation.ReservationViewRequestDto;
import com.gogym.apiserver.dto.trainer.TrainerSaveRequestDto;
import com.gogym.apiserver.entity.Reservation;
import com.gogym.apiserver.error.common.ErrorCode;
import com.gogym.apiserver.error.common.ErrorResponse;
import com.gogym.apiserver.repository.ReservationRepository;
import com.gogym.apiserver.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public List<Reservation> getScheduleByTrainer(ReservationViewRequestDto requestDto) {
        return reservationRepository.getUsersByTrainerId(requestDto.getTrainerId());
    }

    public List<Reservation> getScheduleByUserPhone(ReservationViewRequestDto requestDto) {
        return reservationRepository.getScheduleByUserPhone(requestDto.getTrainerId(), requestDto.getUserPhone());
    }

    public List<Reservation> getScheduleByTime(ReservationViewRequestDto requestDto) {
        return reservationRepository.getScheduleByTime(requestDto.getTrainerId(), requestDto.getStartTime(), requestDto.getEndTime());
    }

    @Transactional
    public Reservation addSchedule(ReservationSaveRequestDto requestDto) {

        return reservationRepository.save(makeReservation(requestDto));
    }


    private Reservation makeReservation(ReservationSaveRequestDto requestDto) {
        return Reservation.builder()
                .trainerId(requestDto.getTrainerId())
                .userPhone(requestDto.getUserPhone())
                .startTime(requestDto.getStartTime())
                .endTime(requestDto.getEndTime())
                .description(requestDto.getDescription())
                .usageState(requestDto.getUsageState())
                .build();
    }

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
}
