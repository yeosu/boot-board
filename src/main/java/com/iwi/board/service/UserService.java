package com.iwi.board.service;

import com.iwi.board.dto.UserDto;
import com.iwi.board.model.UserEntity;
import com.iwi.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();

        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        if(userEntity.getUserId().equals(userId)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new User(userEntity.getUserId(), userEntity.getUserPassword(), authorities);
    }

    public void insertJoinUser(UserDto userDto) throws Exception {
        if (isDupUserId(userDto.getUserId())) {
            throw new IllegalArgumentException("중복된 아이디입니다.");
        }

        String encodedPassword = passwordEncoder.encode(userDto.getUserPassword()); // 비밀번호 암호화

        // UserEntity 생성 및 저장
        UserEntity userEntity = new UserEntity();
        userEntity.setId(GenerateId());
        userEntity.setUserId(userDto.getUserId());
        userEntity.setUserPassword(encodedPassword);
        userEntity.setUserName(userDto.getUserName());
        userEntity.setNickname(userDto.getNickname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setBirth(userDto.getBirth().replace("-",""));
        userEntity.setRegUser(userEntity.getId().toString());
        userEntity.setRegDt(LocalDateTime.now());
        // ... 기타 필요한 정보 설정
        userRepository.save(userEntity);

    }

    public boolean isDupUserId(String userId) {
        Optional<UserEntity> existingUser = userRepository.findByUserId(userId);
        return existingUser.isPresent(); // 해당 userId에 해당하는 유저가 이미 존재하는지 여부 반환
    }

    private Long GenerateId() {
        Long maxId = userRepository.findMaxId();
        return maxId != null ? maxId + 1 : 1;
    }
}
