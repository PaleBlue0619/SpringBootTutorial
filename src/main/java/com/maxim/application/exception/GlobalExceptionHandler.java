package com.maxim.application.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 处理日期解析错误
    @ExceptionHandler(DateTimeParseException.class)
    public Map<String,Object> handleDateTimeParseException(DateTimeParseException e) {
        logger.error("日期解析错误: {}", e.getMessage());
        Map<String, Object> response = new HashMap<>();
        response.put("error", "日期格式不正确");
        response.put("message", "日期解析错误, 请使用 yyyy-mm-dd 格式");
        response.put("received", e.getParsedString());
        return response;
    }

    // 处理参数类型错误
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        logger.error("参数类型错误: {}={}", e.getName(), e.getValue());
        Map<String, Object> response = new HashMap<>();
        response.put("error", "参数类型错误");
        response.put("parameter", e.getName());
        response.put("value", e.getValue());
        response.put("requiredType", e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "未知");
        return response;
    }
}
