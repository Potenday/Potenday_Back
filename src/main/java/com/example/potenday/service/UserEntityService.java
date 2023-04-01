package com.example.potenday.service;

import com.example.potenday.domain.UserEntity;
import com.example.potenday.dto.request.UserRequest;
import com.example.potenday.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;

    public void registerUser(UserRequest request) {
        userEntityRepository.save(UserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .fullname(request.getFullname())
                .nickname(request.getNickname())
                .build());
    }
}
