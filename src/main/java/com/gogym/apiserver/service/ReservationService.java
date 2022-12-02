package com.gogym.apiserver.service;

import com.gogym.apiserver.dto.reservation.ReservationSaveRequestDto;
import com.gogym.apiserver.dto.reservation.ReservationViewRequestDto;
import com.gogym.apiserver.dto.reservation.wrapper.ReservationWrapper;
import com.gogym.apiserver.dto.trainer.TrainerSaveRequestDto;
import com.gogym.apiserver.entity.Reservation;
import com.gogym.apiserver.repository.ReservationRepository;
import com.gogym.apiserver.utils.DateUtil;
import com.gogym.apiserver.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Reservation addSchedule(ReservationSaveRequestDto requestDto) {

        return reservationRepository.save(makeReservation(requestDto));
    }


    private Reservation makeReservation(ReservationSaveRequestDto requestDto) {
        return Reservation.builder()
                .trainerId(SecurityUtil.getCurrentTrainerId().get())
                .userPhone(requestDto.getUserPhone())
                .startTime(requestDto.getStartTime())
                .endTime(requestDto.getEndTime())
                .description(requestDto.getDescription())
                .usageState(requestDto.getUsageState())
                .build();
    }
}
