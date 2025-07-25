package com.hoffmann.songify.infrastructure.crud.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ArtistUpdateRequestDto(
        @NotNull(message = "newArtistName must not be null")
        @NotEmpty(message = "newArtistName must not be empty")
        String newArtistName) {
}