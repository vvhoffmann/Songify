package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.GenreDto;
import org.springframework.stereotype.Service;

@Service
class GenreAdder {

    private final GenreRepository genreRepository;

    public GenreAdder(final GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    GenreDto addGenre(String name) {
        Genre genre = new Genre(name);
        final Genre savedGenre = genreRepository.save(genre);
        return new GenreDto(savedGenre.getId(), savedGenre.getName());
    }
}
