package com.hoffmann.songify.domain.crud.dto;

import lombok.Builder;

@Builder
public record ArtistRequestDto(String name) {
}