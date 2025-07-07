package com.hoffmann.songify.song.infrastructure.controller;

import com.hoffmann.songify.song.domain.service.SongAdder;
import com.hoffmann.songify.song.domain.service.SongMapper;
import com.hoffmann.songify.song.domain.service.SongRetriever;
import com.hoffmann.songify.song.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.hoffmann.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import com.hoffmann.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.hoffmann.songify.song.infrastructure.controller.dto.response.*;
import com.hoffmann.songify.song.domain.model.SongNotFoundException;
import com.hoffmann.songify.song.domain.model.SongEntity;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequestMapping("/songs")
public class SongRestController {

    private final SongAdder songAdder;
    private final SongRetriever songRetriever;

    public SongRestController(SongAdder songAdder, SongRetriever songRetriever) {
        this.songAdder = songAdder;
        this.songRetriever = songRetriever;
    }

    @GetMapping
    public ResponseEntity<GetAllSongsResponseDto> getAllSongs(@RequestParam(required = false) Integer limit) {
        Map<Integer, SongEntity> allSongs = songRetriever.findAll();
        if (limit != null) {
            Map<Integer, SongEntity> limitedMap = songRetriever.findAllLimitedBy(limit).entrySet()
                    .stream()
                    .limit(limit)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            GetAllSongsResponseDto response = new GetAllSongsResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }

        GetAllSongsResponseDto response = SongMapper.mapSongToGetAllSongsResponseDto(allSongs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSingleSongResponseDto> getSongById(@PathVariable("id") Integer id, @RequestHeader(required = false) String requestId) {

        log.info(requestId);
        if (!songRetriever.findAll().containsKey(id))
            throw new SongNotFoundException("Song with id " + id + " doesn't exist");

        SongEntity song = songRetriever.findAll().get(id);
        GetSingleSongResponseDto response = SongMapper.mapSongToGetSingleSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request)
    {
        SongEntity song = SongMapper.mapFromCreateSongRequestDtoToSong(request);
        SongEntity newSong = songAdder.addSong(song);
        CreateSongResponseDto body = SongMapper.mapFromSongToCreateSongResponseDto(song);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Integer id)
    {
        if(!songRetriever.findAll().containsKey(id))
            throw  new SongNotFoundException("Song with id " + id + " doesn't exist");
        
        songRetriever.findAll().remove(id);
        DeleteSongResponseDto body = SongMapper.mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateSongResponseDto> updateSong(@PathVariable Integer id,
                                                        @RequestBody @Valid UpdateSongRequestDto request)
    {
        Map<Integer, SongEntity> allSongs = songRetriever.findAll();
        if(!allSongs.containsKey(id))
            throw  new SongNotFoundException("Song with id " + id + " doesn't exist");
        SongEntity newSong = SongMapper.mapUpdateSongRequestDtoToSong(request);
        SongEntity oldSong = allSongs.put(id, newSong);

        log.info("updated song with id: " + id + " with old song " + oldSong + " to " + newSong);
        UpdateSongResponseDto updateSongResponseDto = SongMapper.mapSongToUpdateSongResponseDto(newSong);
        return ResponseEntity.ok(updateSongResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> updateSongPartially(@PathVariable Integer id,
                                                                              @RequestBody PartiallyUpdateSongRequestDto request)
    {
        Map<Integer, SongEntity> allSongs = songRetriever.findAll();
        if(!allSongs.containsKey(id))
            throw  new SongNotFoundException("Song with id " + id + " doesn't exist");

        SongEntity oldSong = allSongs.get(id);
        SongEntity updatedSong = SongMapper.mapPartiallyUpdateSongRequestDtoToSong(request, oldSong);
        allSongs.put(id, updatedSong);

        log.info("updated song with id: " + id + " with old song " + oldSong + " to " + updatedSong);
        PartiallyUpdateSongResponseDto partiallyUpdateSongResponseDto = SongMapper.mapSongToPartiallyUpdateSongDto(updatedSong);
        return ResponseEntity.ok(partiallyUpdateSongResponseDto);
    }
}