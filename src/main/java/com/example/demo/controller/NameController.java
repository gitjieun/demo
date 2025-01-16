package com.example.demo.controller;

import com.example.demo.test.JndiConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j
@Controller
public class NameController {

    @GetMapping("/")
    public String nameForm() {
        return "name-form"; // templates/name-form.html 표시
    }

    @GetMapping("/name")
    public String nameTemplate(@RequestParam String id) {

        log.info("###### id : {}", id);

        // JNDI Connection 테스트 실행
        JndiConnection connection = new JndiConnection();
        String result = connection.testConnection(id);
        log.info("쿼리 결과: {}", result);

        return "name-template";
    }
}

