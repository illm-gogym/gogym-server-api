package com.gogym.apiserver.service;

import com.gogym.apiserver.dto.registration.RegistrationRequestDtoByUserPhone;
import com.gogym.apiserver.dto.registration.wrapper.RegistrationWrapper;
import com.gogym.apiserver.dto.user.UserSaveRequestDto;
import com.gogym.apiserver.entity.Registration;
import com.gogym.apiserver.repository.registration.RegistrationRepository;
import com.gogym.apiserver.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                .trainerId(requestDto.getTrainerId())
                .userPhone(requestDto.getPhone())
                .total(requestDto.getTotal())
                .status(1)
                .remaining(requestDto.getRemaining())
                .date(DateUtil.stringToDate(requestDto.getUntil()))
                .build();
    }

    public Long getRegistrationId(String trainerId, String userPhone) {
        return registrationRepository.getRegistrationIdByTrainerIdAndUserPhone(trainerId, userPhone);
    }

    public List<Registration> getRegistrationByUserPhone(String userPhone) {
        return registrationRepository.getRegistrationByUserPhone(userPhone);
    }

    public List<Registration> getRegistrationByTrainerId(String trainerId) {
        return registrationRepository.getRegistrationByTrainerId(trainerId);
    }

    public RegistrationWrapper getRegistrationAndDetailByUserPhone(String trainerId, RegistrationRequestDtoByUserPhone requestDto) {
        return registrationRepository.getRegistrationAndDetailByUserPhone(trainerId, requestDto.getUserPhone());
    }
}
