package com.hoffmann.songify.infrastructure.crud.controller.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteArtistResponseDto(String message, HttpStatus httpStatus) {
}