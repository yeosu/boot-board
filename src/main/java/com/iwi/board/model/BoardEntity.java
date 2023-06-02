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
@Builder
@Table(name="TB_BOARD")
@Entity
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="content", nullable = false)
    private String content;

    @Column(name="reg_dt", nullable = false)
    private LocalDateTime regDt;
    @Column(name="reg_user", nullable = false)
    private String regUser;
    @Column(name="upd_dt")
    private LocalDateTime updDt;
    @Column(name="upd_user")
    private String updUser;
}
