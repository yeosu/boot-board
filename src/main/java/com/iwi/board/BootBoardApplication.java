package com.iwi.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BootBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootBoardApplication.class, args);
    }

}
