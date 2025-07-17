package com.hoffmann.songify.infrastructure.crud.controller;

import com.hoffmann.songify.domain.crud.SongifyCrudFacade;
import com.hoffmann.songify.domain.crud.dto.AlbumDto;
import com.hoffmann.songify.domain.crud.dto.AlbumRequestDto;
import com.hoffmann.songify.domain.crud.dto.AlbumWithArtistsAndSongsDto;
import com.hoffmann.songify.domain.crud.dto.SongRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/album")
class AlbumRestController {

    private final SongifyCrudFacade songifyCrudFacade;

    public AlbumRestController(final SongifyCrudFacade songifyCrudFacade) {
        this.songifyCrudFacade = songifyCrudFacade;
    }

    @PostMapping
    ResponseEntity<AlbumDto> createAlbum(@RequestBody AlbumRequestDto requestDto, @RequestBody SongRequestDto songRequestDto) {
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(requestDto, songRequestDto);
        return ResponseEntity.ok(albumDto);
    }

    @GetMapping("/{albumId}")
    ResponseEntity<AlbumWithArtistsAndSongsDto> createAlbum(@PathVariable Long albumId) {
        final AlbumWithArtistsAndSongsDto albumByIdWithArtistsAndSongs = songifyCrudFacade.findAlbumByIdWithArtistsAndSongs(albumId);
        return ResponseEntity.ok(albumByIdWithArtistsAndSongs);
    }
}