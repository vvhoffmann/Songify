package com.hoffmann.songify.infrastructure.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

record ApiValidationErrorResponseDto(List<String> message, HttpStatus httpStatus) {
}
