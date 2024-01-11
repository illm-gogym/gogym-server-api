package com.gogym.apiserver.service;

import com.gogym.apiserver.dto.registration.UserAllDto;
import com.gogym.apiserver.dto.registration.wrapper.RegistrationWrapper;
import com.gogym.apiserver.dto.user.UserSaveRequestDto;
import com.gogym.apiserver.entity.User;
import com.gogym.apiserver.error.common.ErrorCode;
import com.gogym.apiserver.error.exception.NotExistUserException;
import com.gogym.apiserver.error.exception.PhoneNumberDuplicateException;
import com.gogym.apiserver.repository.UserRepository;
import com.gogym.apiserver.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RegistrationService registrationService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByUserPhone(String userPhone) {
        Optional<User> user = userRepository.findByUserPhone(userPhone);
        if (user.orElse(null) == null) {
            throw new NotExistUserException("does not exist user", ErrorCode.NOT_FOUND_USER);
        }
        return user.get();
    }

    public List<UserAllDto> getUsersByTrainerId(String trainerId) {
        List<RegistrationWrapper> res = registrationService.getRegistrationAndUserByTrainerId(trainerId);
        List<UserAllDto> userList = new ArrayList<>();
        for (RegistrationWrapper re : res) {
            userList.add(UserAllDto.builder()
                    .name(re.getUser().getName())
                    .user_phone(re.getUser().getUserPhone())
                    .gender(re.getUser().getGender())
                    .ins_dtm(re.getUser().getCreatedDate().toString())
                    .upd_dtm(re.getUser().getUpdateDateTime().toString())
                    .start_date(re.getRegistration().getStartDate().toString())
                    .end_date(re.getRegistration().getEndDate().toString())
                    .build());
        }
        return userList;
    }

    @Transactional
    public User signup(UserSaveRequestDto requestDto) {
        if (userRepository.findByUserPhone(requestDto.getPhone()).orElse(null) != null) {
            throw new PhoneNumberDuplicateException("phoneNumber duplicated", ErrorCode.DUPLICATED_PHONE_NUMBER);
        }

        registrationService.addRegistration(requestDto);
        return userRepository.save(makeUser(requestDto));
    }

    private User makeUser(UserSaveRequestDto requestDto) {
        return User.builder()
                .userPhone(requestDto.getPhone())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .gender(requestDto.getGender())
                .date(DateUtil.stringToDate(requestDto.getBirth()))
                .role(requestDto.getRole())
                .build();
    }
}
