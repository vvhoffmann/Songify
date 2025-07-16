package com.hoffmann.songify.domain.crud;

class AlbumNotFoundException extends RuntimeException {
    AlbumNotFoundException(final String message) {
        super(message);
    }
}