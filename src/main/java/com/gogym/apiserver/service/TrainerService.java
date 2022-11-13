package com.gogym.apiserver.service;

import com.gogym.apiserver.dto.trainer.TrainerSaveRequestDto;
import com.gogym.apiserver.entity.Trainer;
import com.gogym.apiserver.entity.User;
import com.gogym.apiserver.error.common.ErrorCode;
import com.gogym.apiserver.error.exception.PhoneNumberDuplicateException;
import com.gogym.apiserver.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Trainer signup(TrainerSaveRequestDto requestDto) {
        if (trainerRepository.findByTrainerId(requestDto.getTrainerId()).orElse(null) != null) {
            throw new PhoneNumberDuplicateException("trainer id duplicated", ErrorCode.TRAINER_ID_DUPLICATION);
        }

        return trainerRepository.save(makeTrainer(requestDto));
    }

    private Trainer makeTrainer(TrainerSaveRequestDto requestDto) {
        return Trainer.builder()
                .trainerId(requestDto.getTrainerId())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .trainerPhone(requestDto.getPhone())
                .gymId(requestDto.getGymId())
                .role(requestDto.getRole())
                .build();
    }
}
