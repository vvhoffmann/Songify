package com.hoffmann.songify.infrastructure.crud.controller;

import com.hoffmann.songify.domain.crud.SongifyCrudFacade;
import com.hoffmann.songify.domain.crud.dto.GenreDto;
import com.hoffmann.songify.domain.crud.dto.GenreRequestDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.CreateGenreResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
class GenreRestController {

    private final SongifyCrudFacade songifyCrudFacade;

    public GenreRestController(SongifyCrudFacade songifyCrudFacade) {
        this.songifyCrudFacade = songifyCrudFacade;
    }

    @PostMapping("/{name}")
    public ResponseEntity<CreateGenreResponseDto> addGenre(@RequestBody GenreRequestDto name) {
        GenreDto genreDto = songifyCrudFacade.addGenre(name);
        CreateGenreResponseDto body = GenreControllerMapper.mapFromGenreDtoToCreateGenreResponseDto(genreDto);
        return ResponseEntity.ok(body);
    }

}
