package com.hoffmann.songify.infrastructure.crud.controller;

import com.hoffmann.songify.domain.crud.SongifyCrudFascade;
import com.hoffmann.songify.domain.crud.dto.AlbumDto;
import com.hoffmann.songify.domain.crud.dto.AlbumRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/album")
class AlbumRestController {

    private final SongifyCrudFascade songifyCrudFascade;

    public AlbumRestController(final SongifyCrudFascade songifyCrudFascade) {
        this.songifyCrudFascade = songifyCrudFascade;
    }

    @PostMapping
    ResponseEntity<AlbumDto> createAlbum(@RequestBody AlbumRequestDto requestDto) {
        AlbumDto albumDto = songifyCrudFascade.addAlbumWithSong(requestDto);
        return ResponseEntity.ok(albumDto);
    }
}