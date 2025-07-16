package com.hoffmann.songify.infrastructure.apivalidation.exception;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(final String message) {
        super(message);
    }
}
