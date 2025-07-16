package com.hoffmann.songify.infrastructure.apivalidation.exception;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(final String message) {
        super(message);
    }
}