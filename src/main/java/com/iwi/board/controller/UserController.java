package com.iwi.board.controller;

import com.iwi.board.dto.UserDto;
import com.iwi.board.service.UserService;
import com.iwi.board.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender javaMailSender;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> userLogin(@RequestBody Map<String, String> paramMap) {
        String userId = paramMap.get("user_id");
        String userPassword = paramMap.get("user_password");

        UserDetails loginUser = userService.loadUserByUsername(userId);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser, userPassword)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtil.createToken(loginUser.getUsername(), loginUser.getUsername());

        Map<String, Object> result = new HashMap<>();
        result.put("user_id", loginUser.getUsername());
        result.put("user_token", accessToken);
        result.put("user_role", loginUser.getAuthorities().stream().findFirst().get().getAuthority());

        return ResponseEntity.ok(result);

    }

    @PostMapping("/join")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) throws Exception {
        try {
            userService.insertJoinUser(userDto);
            return new ResponseEntity<>("회원가입이 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("회원가입 중 오류가 발생했습니다. 다시 시도해주세요.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody EmailRequest emailRequest){
        SimpleMailMessage message = new SimpleMailMessage();
        String authCode = generateAuthCode();
        message.setTo(emailRequest.getEmail());
        message.setSubject("이메일 인증 코드");
        message.setText("인증 코드 : " + authCode);
        javaMailSender.send(message);

        return authCode;
    }

    @PostMapping("/checkEmailAuth")
    public String checkEmailAuth(@RequestBody Map<String, String> paramMap) {
        String serverAuthCode = paramMap.get("serverAuthCode");
        String inputAuthCode = paramMap.get("emailAuthCode");
        String result = "";
        if(serverAuthCode.equals(inputAuthCode)){
            result="Success";
        }else{
            result="fail";
        }
        return result;
    }

    private String generateAuthCode() {
        return String.valueOf((int)(Math.random() * 900000) + 100000);
    }

    private static class EmailRequest {
        private String email;

        public String getEmail(){
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
    }
}
