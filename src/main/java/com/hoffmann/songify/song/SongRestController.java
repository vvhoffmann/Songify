package com.hoffmann.songify.song;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
public class SongRestController {

    Map<Integer, String> database = new HashMap<>(Map.of(
            1, "Shawn Mendes song",
            2, "Araia song",
            3, "Shivers",
            4, "Bad blood"
    ));

    @GetMapping("/songs")
    public ResponseEntity<SongResponseDto> getAllSongs(@RequestParam(required = false) Integer limit) {

        if (limit != null) {
            Map<Integer, String> limitedMap = database.entrySet()
                    .stream()
                    .limit(limit)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            SongResponseDto response = new SongResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }

        SongResponseDto response = new SongResponseDto(database);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SingleSongReponseDto> getSongById(@PathVariable("id") Integer id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        String song = database.get(id);
        if (song == null) {
            return ResponseEntity.notFound().build();
        }
        SingleSongReponseDto response = new SingleSongReponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/songs")
    public ResponseEntity<SingleSongReponseDto> postSong(@RequestBody @Valid SongRequestDto request)
    {
        String songName = request.songName();
        database.put(database.size() + 1, request.songName());
        log.info("added new song: " + songName);
        return ResponseEntity.ok(new SingleSongReponseDto(songName));
    }
}