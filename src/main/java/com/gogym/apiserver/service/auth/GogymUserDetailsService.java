package com.gogym.apiserver.service.auth;

import com.gogym.apiserver.entity.Trainer;
import com.gogym.apiserver.entity.User;
import com.gogym.apiserver.error.common.ErrorCode;
import com.gogym.apiserver.error.exception.LoginException;
import com.gogym.apiserver.repository.TrainerRepository;
import com.gogym.apiserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GogymUserDetailsService implements UserDetailsService {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;

    // DB에서 유저정보를 가져온다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.contains("010")) {
            System.out.printf("load user by user name : " + username);
            return userRepository.findByUserPhone(username)
                    .map(user -> createUserDetails(user))
                    .orElseThrow(() -> new LoginException(username + " 존재하지 않는 user 입니다.", ErrorCode.NOT_FOUND_USERPHONE));
        }
        return trainerRepository.findByTrainerId(username)
                .map(user -> createUserDetails(user))
                .orElseThrow(() -> new LoginException(username + " 존재하지 않는 trainer 입니다.", ErrorCode.NOT_FOUND_TRAINER));
    }

    // DB에서 조회한 user 정보를 기반으로 UserDetails의 구현체인
    // User (org.springframework.security.core.userdetails.User) 를 생성하여 return 한다.
    private UserDetails createUserDetails(Trainer trainer) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(trainer.getRole().toString());

        return new org.springframework.security.core.userdetails.User(
                trainer.getTrainerId(),
                trainer.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }

    private UserDetails createUserDetails(User user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());

        return new org.springframework.security.core.userdetails.User(
                user.getUserPhone(),
                user.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}