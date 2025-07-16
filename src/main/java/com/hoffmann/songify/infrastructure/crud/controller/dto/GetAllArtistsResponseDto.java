package com.hoffmann.songify.infrastructure.crud.controller.dto;

import com.hoffmann.songify.domain.crud.dto.ArtistDto;

import java.util.Set;

public record GetAllArtistsResponseDto(Set<ArtistDto> artists) {
}