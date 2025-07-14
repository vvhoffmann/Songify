package com.hoffmann.songify.domain.crud.song.dto;

import lombok.Builder;

@Builder
public record SongDto(String name, String artist) {
}