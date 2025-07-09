package com.hoffmann.songify.song.infrastructure.controller;

import com.hoffmann.songify.song.domain.service.*;
import com.hoffmann.songify.song.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.hoffmann.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import com.hoffmann.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.hoffmann.songify.song.infrastructure.controller.dto.response.*;
import com.hoffmann.songify.song.domain.model.SongEntity;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/songs")
public class SongRestController {

    private final SongAdder songAdder;
    private final SongRetriever songRetriever;
    private final SongDeleter songDeleter;
    private final SongUpdater songUpdater;

    public SongRestController(SongAdder songAdder, SongRetriever songRetriever, SongDeleter songDeleter, SongUpdater songUpdater) {
        this.songAdder = songAdder;
        this.songRetriever = songRetriever;
        this.songDeleter = songDeleter;
        this.songUpdater = songUpdater;
    }

    @GetMapping
    public ResponseEntity<GetAllSongsResponseDto> getAllSongs(@RequestParam(required = false) Integer limit) {
        List<SongEntity> allSongs = songRetriever.findAll();
        if (limit != null) {
            List<SongEntity> limitedMap = songRetriever.findAllLimitedBy(limit)
                    .stream()
                    .limit(limit)
                    .toList();
            GetAllSongsResponseDto response = new GetAllSongsResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }

        GetAllSongsResponseDto response = SongMapper.mapSongToGetAllSongsResponseDto(allSongs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSingleSongResponseDto> getSongById(@PathVariable("id") Long id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        SongEntity song = songRetriever.findSongById(id);

        GetSingleSongResponseDto response = SongMapper.mapSongToGetSingleSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request) {
        SongEntity song = SongMapper.mapFromCreateSongRequestDtoToSong(request);
        songAdder.save(song);
        CreateSongResponseDto body = SongMapper.mapFromSongToCreateSongResponseDto(song);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Long id) {
        songDeleter.deleteById(id);
        DeleteSongResponseDto body = SongMapper.mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateSongResponseDto> updateSong(@PathVariable Long id,
                                                        @RequestBody @Valid UpdateSongRequestDto request)
    {
        SongEntity newSong = SongMapper.mapUpdateSongRequestDtoToSong(request);
        songUpdater.updateById(id, newSong);

        UpdateSongResponseDto updateSongResponseDto = SongMapper.mapSongToUpdateSongResponseDto(newSong);
        return ResponseEntity.ok(updateSongResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> updateSongPartially(@PathVariable Long id,
                                                                              @RequestBody PartiallyUpdateSongRequestDto request)
    {
        SongEntity updatedSong = SongMapper.mapPartiallyUpdateSongRequestDtoToSong(request);
        SongEntity savedSong = songUpdater.updatePartiallyById(id, updatedSong);

        log.info("Updated song: {}", savedSong);

        PartiallyUpdateSongResponseDto body = SongMapper.mapSongToPartiallyUpdateSongDto(savedSong);
        return ResponseEntity.ok(body);
    }
}