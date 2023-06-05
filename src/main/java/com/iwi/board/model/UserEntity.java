package com.iwi.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TB_USER")
@Builder
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", nullable = false, unique = true)
    private String userId;

    @Column(name="user_name", nullable = false)
    private String userName;

    @Column(name="nickname")
    private String nickname;

    @Column(name="user_password")
    private String userPassword;

    @Column(name="email")
    private String email;

    @Column(name="birth")
    private String birth;

    @Column(name="reg_dt")
    private LocalDateTime regDt;
    @Column(name="reg_user")
    private String regUser;
    @Column(name="upd_dt")
    private LocalDateTime updDt;
    @Column(name="upd_user")
    private String updUser;
}
