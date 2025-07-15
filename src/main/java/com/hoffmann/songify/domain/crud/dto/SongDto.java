package com.hoffmann.songify.domain.crud.dto;

import lombok.Builder;

@Builder
public record SongDto(String name, String artist) {
}