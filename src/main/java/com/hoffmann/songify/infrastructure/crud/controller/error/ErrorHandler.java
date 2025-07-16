package com.hoffmann.songify.infrastructure.crud.controller.error;

import com.hoffmann.songify.infrastructure.apivalidation.exception.ArtistNotFoundException;
import com.hoffmann.songify.infrastructure.apivalidation.exception.SongNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
class ErrorHandler {

    @ExceptionHandler(SongNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorSongResponseDto handleException(SongNotFoundException exception) {
        log.warn("error while accessing song. Wrong id");
        return new ErrorSongResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArtistNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorArtistResponseDto handleException(ArtistNotFoundException exception) {
        log.warn("error while accessing song. Wrong id");
        return new ErrorArtistResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}