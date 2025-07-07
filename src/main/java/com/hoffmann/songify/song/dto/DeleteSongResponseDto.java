package com.hoffmann.songify.song.dto;

import org.springframework.http.HttpStatus;

public record DeleteSongResponseDto (String message, HttpStatus status) {
}
