package com.hoffmann.songify.infrastructure.crud.controller.error;

import org.springframework.http.HttpStatus;

record ErrorArtistResponseDto (String message, HttpStatus httpStatus){
}
