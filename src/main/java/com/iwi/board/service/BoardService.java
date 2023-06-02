package com.iwi.board.service;

import com.iwi.board.dto.BoardDto;
import com.iwi.board.model.BoardEntity;
import com.iwi.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    
    public List<BoardDto> getBoardList() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> dtos = new ArrayList<>();

        for (BoardEntity entity: boardEntities) {
            BoardDto dto = BoardDto.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .reg_dt(entity.getRegDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .reg_user(entity.getRegUser())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }

    public BoardDto getBoard(Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return BoardDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .reg_dt(entity.getRegDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .reg_user(entity.getRegUser())
                .build();
    }

    public BoardEntity insertBoard(BoardDto boardDto) {
        BoardEntity entity = BoardEntity.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .regDt(LocalDateTime.now())
                .regUser(boardDto.getReg_user())
                .build();
        return boardRepository.save(entity);
    }

    public BoardEntity updateBoard(BoardDto boardDto) {
        BoardEntity entity = boardRepository.findById(boardDto.getId()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        entity.setTitle(boardDto.getTitle());
        entity.setContent(boardDto.getContent());
        return boardRepository.save(entity);
    }

    public void deleteBoard(Long id){
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(entity);
    }
}
