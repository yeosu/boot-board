package com.iwi.board.dto;

import com.iwi.board.model.UserEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {

    @NonNull
    private String userId;
    @NonNull
    private String userName;
    @NonNull
    private String nickname;
    @NonNull
    private String userPassword;
    private String email;
    private String birth;
    private String reg_user;
    private LocalDateTime reg_dt;

    public UserEntity toEntity() {
        UserEntity userEntity = UserEntity.builder()
                .userId(userId)
                .userName(userName)
                .nickname(nickname)
                .email(email)
                .birth(birth)
                .build();
        return userEntity;
    }

}
