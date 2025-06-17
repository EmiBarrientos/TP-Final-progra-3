package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(NoSuchElementException.class)
        public ResponseEntity<Object> handleNotFound(NoSuchElementException ex, HttpServletRequest request) {
            return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
            return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request.getRequestURI());
        }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
            return buildResponse("Tipo de argumento incorrecto: " + ex.getName(), HttpStatus.BAD_REQUEST, request.getRequestURI());
        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<Object> handleGeneral(RuntimeException ex, HttpServletRequest request) {
            return buildResponse("Error inesperado: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
        }

        private ResponseEntity<Object> buildResponse(String message, HttpStatus status, String path) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now().toString());
            body.put("status", status.value());
            body.put("error", status.getReasonPhrase());
            body.put("message", message);
            body.put("path", path);
            return new ResponseEntity<>(body, status);
        }
    }


