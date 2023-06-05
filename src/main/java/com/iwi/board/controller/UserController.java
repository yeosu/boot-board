package com.iwi.board.controller;

import com.iwi.board.dto.UserDto;
import com.iwi.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public void userJoin(@RequestBody UserDto userDto) throws Exception {
        userService.joinUser(userDto);

    }

}
