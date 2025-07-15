package com.hoffmann.songify.domain.crud.dto;

import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;

public record SongRequestDto(
        @NotEmpty(message = "Song name should't be null")
        @NotEmpty(message = "Song name should't be empty")
        String name,

        Instant releaseDate,
        Long duration,
        SongLanguageDto language
) {
}
