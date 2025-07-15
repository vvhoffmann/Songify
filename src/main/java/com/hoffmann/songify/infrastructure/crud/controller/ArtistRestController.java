package com.hoffmann.songify.infrastructure.crud.controller;

import com.hoffmann.songify.domain.crud.SongifyCrudFascade;
import com.hoffmann.songify.domain.crud.dto.ArtistRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/artist")
class ArtistRestController {

    private final SongifyCrudFascade songifyCrudFascade;

    @PostMapping("/{name}")
    ResponseEntity<CreateArtistResponseDto> addArtist(@RequestBody ArtistRequestDto artistToAdd) {
        songifyCrudFascade.addArtist(artistToAdd);
        CreateArtistResponseDto responseDto = ArtistControllerMapper.mapFromArtistDtoToCreateArtistResponseDto(artistToAdd);
        return ResponseEntity.ok(responseDto);
    }

}