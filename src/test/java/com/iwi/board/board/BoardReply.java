package com.iwi.board.board;

import java.time.LocalDateTime;

public class BoardReply {
    private Long id;
    private Board board;
    private String content;

    private LocalDateTime regDt;
    private String regUser;
    private LocalDateTime updDt;
    private String updUser;
}
