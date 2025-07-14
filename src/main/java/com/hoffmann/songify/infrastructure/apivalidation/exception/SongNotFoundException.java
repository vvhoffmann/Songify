package com.hoffmann.songify.infrastructure.apivalidation.exception;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(String message) {
        super(message);
    }
}
