package com.patika.hw5.controller;

import com.patika.hw5.service.ExceptionLoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exception-logs")
@RequiredArgsConstructor
public class ExceptionLoggerController {
    private ExceptionLoggerService exceptionLoggerService;
    @Autowired
    public ExceptionLoggerController(ExceptionLoggerService exceptionLoggerService) {
        this.exceptionLoggerService = exceptionLoggerService;
    }
}
