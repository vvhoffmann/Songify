package com.hoffmann.songify.infrastructure.crud.controller;

import com.hoffmann.songify.domain.crud.SongifyCrudFacade;
import com.hoffmann.songify.domain.crud.dto.SongDto;
import com.hoffmann.songify.domain.crud.dto.SongRequestDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.CreateSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.DeleteSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.GetAllSongsResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.GetSingleSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.PartiallyUpdateSongResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/songs")
@AllArgsConstructor
public class SongRestController {

    private final SongifyCrudFacade songifyCrudFacade;

    @GetMapping
    public ResponseEntity<GetAllSongsResponseDto> getAllSongs(Pageable pageable) {
        List<SongDto> allSongs = songifyCrudFacade.findAllSongs(pageable);
        GetAllSongsResponseDto response = SongControllerMapper.mapFromSongDtoToGetAllSongsResponseDto(allSongs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSingleSongResponseDto> getSongById(@PathVariable("id") Long id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        SongDto song = songifyCrudFacade.findSongById(id);
        GetSingleSongResponseDto response = SongControllerMapper.mapSongDtoToGetSingleSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid SongRequestDto request) {
        SongDto songDto = songifyCrudFacade.saveSongWithArtist(request);
        CreateSongResponseDto body = SongControllerMapper.mapFromSongDtoToCreateSongResponseDto(songDto);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongById(@PathVariable Long id) {
        songifyCrudFacade.deleteSongById(id);
        DeleteSongResponseDto body = SongControllerMapper.mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongDto> updateSong(@PathVariable Long id,
                                              @RequestBody @Valid SongRequestDto request) {
        final SongDto songDto = songifyCrudFacade.updateSongById(id, request);
        return ResponseEntity.ok(songDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> updateSongPartially(@PathVariable Long id,
                                                                              @RequestBody SongRequestDto request) {
        SongDto savedSong = songifyCrudFacade.updatePartiallySongById(id, request);

        log.info("Updated song: {}", savedSong);

        PartiallyUpdateSongResponseDto body = SongControllerMapper.mapFromSongToPartiallyUpdateSongDto(savedSong);
        return ResponseEntity.ok(body);
    }
}