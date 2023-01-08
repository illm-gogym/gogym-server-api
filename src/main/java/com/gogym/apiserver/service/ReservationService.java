package com.gogym.apiserver.service;

import com.gogym.apiserver.dto.reservation.ReservationDto;
import com.gogym.apiserver.dto.reservation.ReservationSaveRequestDto;
import com.gogym.apiserver.dto.reservation.ReservationUpdateRequestDto;
import com.gogym.apiserver.dto.reservation.select.ReservationRequestDtoByTrainerIdAndTime;
import com.gogym.apiserver.dto.reservation.select.ReservationRequestDtoByUserPhone;
import com.gogym.apiserver.dto.reservation.select.ReservationRequestDtoByUserPhoneAndTime;
import com.gogym.apiserver.dto.reservation.select.ReservationTimeRequestDto;
import com.gogym.apiserver.dto.reservation.wrapper.ReservationWrapper;
import com.gogym.apiserver.entity.Reservation;
import com.gogym.apiserver.error.common.ErrorCode;
import com.gogym.apiserver.error.common.ErrorResponse;
import com.gogym.apiserver.repository.ReservationRepository;
import com.gogym.apiserver.utils.SecurityUtil;
import com.gogym.apiserver.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RegistrationService registrationService;

    public List<ReservationWrapper> getScheduleByTrainer(String trainerId) {
        return reservationRepository.getUsersByTrainerId(trainerId);
    }

    public List<ReservationWrapper> getScheduleByUserPhone(ReservationRequestDtoByUserPhone requestDto) {
        return reservationRepository.getScheduleByUserPhone(SecurityUtil.getCurrentTrainerId().get(), requestDto.getUserPhone());
    }

    public List<ReservationWrapper> getScheduleByUserPhoneAndTime(ReservationRequestDtoByUserPhoneAndTime requestDto) {
        return reservationRepository.getScheduleByUserPhoneAndTime(SecurityUtil.getCurrentTrainerId().get(), requestDto.getUserPhone(), requestDto.getStartTime(), requestDto.getEndTime());
    }

    public List<ReservationWrapper> getScheduleByTime(ReservationTimeRequestDto requestDto) {
        return reservationRepository.getScheduleByTime(SecurityUtil.getCurrentTrainerId().get(), requestDto.getStartTime(), requestDto.getEndTime());
    }

    public List<ReservationWrapper> getScheduleByTrainerIdAndTime(ReservationRequestDtoByTrainerIdAndTime requestDto) {
        return reservationRepository.getScheduleByTrainerIdAndTime(requestDto.getTrainerId(), requestDto.getStartTime(), requestDto.getEndTime());
    }

    @Transactional
    public List<Reservation> addSchedule(ReservationSaveRequestDto requestDto) {
        return reservationRepository.saveAll(makeReservation(requestDto));
    }

    private List<Reservation> makeReservation(ReservationSaveRequestDto requestDto) {
        List<Reservation> reservations = new ArrayList<>();
        for (ReservationDto reservationDto : requestDto.getReservations()) {
            String trainerId = SecurityUtil.getCurrentTrainerId().get();
            String userPhone = reservationDto.getUserPhone();
            log.info("trainer_id={}, user_phone={}", trainerId, userPhone);
            Long registrationId = registrationService.getRegistrationId(trainerId, userPhone);
            log.info("registratiaon_id={}", registrationId);
            System.out.println();
            Reservation reservation = Reservation.builder()
                    .registrationId(registrationId)
                    .trainerId(trainerId)
                    .userPhone(userPhone)
                    .startTime(reservationDto.getStartTime())
                    .endTime(reservationDto.getEndTime())
                    .description(reservationDto.getDescription())
                    .usageState(reservationDto.getUsageState())
                    .build();
            reservations.add(reservation);
        }
        return reservations;
    }

    public Reservation updateSchedule(ReservationUpdateRequestDto requestDto) {

        validateUpdate(requestDto);

        Optional<Reservation> byId = reservationRepository.findById(requestDto.getReservationId());
        if (!byId.isPresent()) {
            new ErrorResponse(ErrorCode.NOT_FOUND_RESERVATION);
        }

        Reservation reservation = byId.get();
        reservation.updateReservation(requestDto.getUserPhone()
                , requestDto.getStartTime()
                , requestDto.getEndTime()
                , requestDto.getDescription()
                , requestDto.getUsageState());

        return reservationRepository.save(reservation);
    }

    private void validateUpdate(ReservationUpdateRequestDto requestDto) {
        if (!ValidationUtil.validPhoneNumber(requestDto.getUserPhone())) {
            new ErrorResponse(ErrorCode.INVALID_PHONE_NUMBER);
        }
        if (requestDto.getStartTime().isBefore(requestDto.getEndTime())) {
            new ErrorResponse(ErrorCode.INVALID_DATE);
        }
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
