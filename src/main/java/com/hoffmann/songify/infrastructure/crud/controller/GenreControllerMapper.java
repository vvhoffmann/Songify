package com.hoffmann.songify.infrastructure.crud.controller;

import com.hoffmann.songify.domain.crud.dto.GenreDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.CreateGenreResponseDto;

class GenreControllerMapper {

    static CreateGenreResponseDto mapFromGenreDtoToCreateGenreResponseDto(final GenreDto genreDto) {
        return new CreateGenreResponseDto(genreDto.id(), genreDto.name());
    }
}
