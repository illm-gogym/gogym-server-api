package com.gogym.apiserver.service;


import com.gogym.apiserver.dto.registration.RegistrationRequestDtoByUserPhone;
import com.gogym.apiserver.dto.registration.wrapper.RegistrationWrapper;
import com.gogym.apiserver.dto.reservation.ReservationUpdateRequestDto;
import com.gogym.apiserver.dto.user.UserSaveRequestDto;
import com.gogym.apiserver.entity.Registration;
import com.gogym.apiserver.error.common.ErrorCode;
import com.gogym.apiserver.error.exception.CommonException;
import com.gogym.apiserver.repository.registration.RegistrationRepository;
import com.gogym.apiserver.utils.CommonUtil;
import com.gogym.apiserver.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;

    @Transactional
    public Registration addRegistration(UserSaveRequestDto requestDto) {

        return registrationRepository.save(makeRegistration(requestDto));
    }

    private Registration makeRegistration(UserSaveRequestDto requestDto) {
        return Registration.builder()
                .gymId(requestDto.getGymId())
                .registrationId(CommonUtil.NewResourceId("reg"))
                .trainerId(requestDto.getTrainerId())
                .userPhone(requestDto.getPhone())
                .total(requestDto.getTotal())
                .status(1)
                .remaining(requestDto.getTotal())
                .startDate(DateUtil.stringToDate(requestDto.getStartDate()))
                .endDate(DateUtil.stringToDate(requestDto.getUntil()))
                .build();
    }

    public String getRegistrationId(String trainerId, String userPhone) {
        return registrationRepository.getRegistrationIdByTrainerIdAndUserPhone(trainerId, userPhone);
    }

    public List<Registration> getRegistrationByUserPhone(String userPhone) {
        return registrationRepository.getRegistrationByUserPhoneList(userPhone);
    }

    public List<Registration> getRegistrationByTrainerId(String trainerId) {
        return registrationRepository.getRegistrationByTrainerId(trainerId);
    }

    public Object updateRegistration(ReservationUpdateRequestDto requestDto) {
        Optional<Registration> byId = registrationRepository.getRegistrationByUserPhone(requestDto.getUserPhone());
        if (byId.isPresent()) {
            throw new CommonException(ErrorCode.NOT_FOUND_RESERVATION);
        }

        Registration registration = byId.get();
        if (!registration.isValidate()) {
            throw new CommonException(ErrorCode.INVALID_REGISTRATION);
        }

        // 업데이트할 데이터 내용 정리 필요
        registration.update();

        return null;
    }
        
    public RegistrationWrapper getRegistrationAndDetailByUserPhone(String trainerId, RegistrationRequestDtoByUserPhone requestDto) {
        return registrationRepository.getRegistrationAndDetailByUserPhone(trainerId, requestDto.getUserPhone());
    }

    public List<RegistrationWrapper> getRegistrationAndUserByTrainerId(String trainerId) {
        return registrationRepository.getRegistrationAndUserByTrainerId(trainerId);
    }


}
