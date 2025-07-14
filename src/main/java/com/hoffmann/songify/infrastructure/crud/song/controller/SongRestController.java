package com.hoffmann.songify.infrastructure.crud.song.controller;

import com.hoffmann.songify.domain.crud.song.SongCrudFascade;
import com.hoffmann.songify.domain.crud.song.dto.SongDto;
import com.hoffmann.songify.infrastructure.crud.song.controller.dto.request.CreateSongRequestDto;
import com.hoffmann.songify.infrastructure.crud.song.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.hoffmann.songify.infrastructure.crud.song.controller.dto.request.UpdateSongRequestDto;
import com.hoffmann.songify.infrastructure.crud.song.controller.dto.response.CreateSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.song.controller.dto.response.DeleteSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.song.controller.dto.response.GetAllSongsResponseDto;
import com.hoffmann.songify.infrastructure.crud.song.controller.dto.response.GetSingleSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.song.controller.dto.response.PartiallyUpdateSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.song.controller.dto.response.UpdateSongResponseDto;
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

    private final SongCrudFascade songCrudFascade;

    @GetMapping
    public ResponseEntity<GetAllSongsResponseDto> getAllSongs(Pageable pageable) {
        List<SongDto> allSongs = songCrudFascade.findAll(pageable);
        GetAllSongsResponseDto response = SongControllerMapper.mapFromSongDtoToGetAllSongsResponseDto(allSongs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSingleSongResponseDto> getSongById(@PathVariable("id") Long id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        SongDto song = songCrudFascade.findSongById(id);
        GetSingleSongResponseDto response = SongControllerMapper.mapSongDtoToGetSingleSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request) {
        SongDto songDto = SongControllerMapper.mapFromCreateSongRequestDtoToSongDto(request);
        songCrudFascade.save(songDto);
        CreateSongResponseDto body = SongControllerMapper.mapFromSongDtoToCreateSongResponseDto(songDto);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Long id) {
        songCrudFascade.deleteById(id);
        DeleteSongResponseDto body = SongControllerMapper.mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateSongResponseDto> updateSong(@PathVariable Long id,
                                                            @RequestBody @Valid UpdateSongRequestDto request) {
        SongDto newSong = SongControllerMapper.mapFromUpdateSongRequestDtoToSongDto(request);
        songCrudFascade.updateById(id, newSong);
        UpdateSongResponseDto updateSongResponseDto = SongControllerMapper.mapFromSongDtoToUpdateSongResponseDto(newSong);
        return ResponseEntity.ok(updateSongResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> updateSongPartially(@PathVariable Long id,
                                                                              @RequestBody PartiallyUpdateSongRequestDto request) {
        SongDto updatedSong = SongControllerMapper.mapFromPartiallyUpdateSongRequestDtoToSongDto(request);
        SongDto savedSong = songCrudFascade.updatePartiallyById(id, updatedSong);

        log.info("Updated song: {}", savedSong);

        PartiallyUpdateSongResponseDto body = SongControllerMapper.mapFromSongToPartiallyUpdateSongDto(savedSong);
        return ResponseEntity.ok(body);
    }
}