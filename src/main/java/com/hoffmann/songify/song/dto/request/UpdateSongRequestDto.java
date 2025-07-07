package com.hoffmann.songify.song.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateSongRequestDto(

        @NotNull(message = "songName must not be null")
        @NotEmpty(message = "songName must not be empty")
        String songName,

        @NotNull(message = "artistName must not be null")
        @NotEmpty(message = "artistName must not be empty")
        String artistName) {
}
