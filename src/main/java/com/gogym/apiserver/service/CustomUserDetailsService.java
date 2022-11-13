package com.gogym.apiserver.service;

import com.gogym.apiserver.entity.Trainer;
import com.gogym.apiserver.entity.User;
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
public class CustomUserDetailsService implements UserDetailsService {

    private final TrainerRepository trainerRepository;

    // DB에서 유저정보를 가져온다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return trainerRepository.findByTrainerId(username)
                .map(user -> createUserDetails(user))
                .orElseThrow(() -> new UsernameNotFoundException(username + " 존재하지 않는 username 입니다."));
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
}