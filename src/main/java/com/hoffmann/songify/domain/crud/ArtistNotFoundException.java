package com.hoffmann.songify.domain.crud;

class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(final String message) {
        super(message);
    }
}
