package com.hoffmann.songify.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiValidationErrorResponseDto(List<String> message, HttpStatus httpStatus) {
}
