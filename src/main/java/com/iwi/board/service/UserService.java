package com.iwi.board.service;

import com.iwi.board.dto.UserDto;
import com.iwi.board.model.UserEntity;
import com.iwi.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserEntity joinUser(UserDto userDto) throws Exception{

        UserEntity entity = UserEntity.builder()
                .userId(userDto.getUserId())
                .userName(userDto.getUserName())
                .userPassword(userDto.getPassword())
                .nickname(userDto.getNickname())
                .email(userDto.getEmail())
                .birth(userDto.getBirth())
                .regUser(userDto.getReg_user())
                .regDt(LocalDateTime.now())
                .build();
        return userRepository.save(entity);
    }


}
