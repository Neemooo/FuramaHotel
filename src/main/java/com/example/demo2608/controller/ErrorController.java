package com.example.demo2608.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/notAdmin")
    public String handleError() {

        return "errors/error"; // Đây là một trang thông báo lỗi
    }

}
