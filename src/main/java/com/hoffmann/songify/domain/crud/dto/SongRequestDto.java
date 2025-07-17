package com.hoffmann.songify.domain.crud.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.Instant;

@Builder
public record SongRequestDto(
        @NotEmpty(message = "Song name should't be null")
        @NotEmpty(message = "Song name should't be empty")
        String name,

        Instant releaseDate,
        Long duration,
        SongLanguageDto language
) {
}
