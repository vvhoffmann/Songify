package com.hoffmann.songify.infrastructure.crud.controller;

import com.hoffmann.songify.domain.crud.dto.GenreDto;

class GenreControllerMapper {

    static CreateGenreResponseDto mapFromGenreDtoToCreateGenreResponseDto(final GenreDto genreDto) {
        return new CreateGenreResponseDto(genreDto.id(), genreDto.name());
    }
}
