package com.hoffmann.songify.infrastructure.crud.controller;

import com.hoffmann.songify.domain.crud.dto.ArtistRequestDto;

class ArtistControllerMapper {

    static CreateArtistResponseDto mapFromArtistDtoToCreateArtistResponseDto(final ArtistRequestDto artistToAdd) {
        return new CreateArtistResponseDto(artistToAdd.name());
    }
}
