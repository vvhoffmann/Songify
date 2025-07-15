package com.hoffmann.songify.infrastructure.crud.controller.dto.response;

import lombok.Builder;

@Builder
public record SongControllerResponseDto(String name, String artist) {
}
