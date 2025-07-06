package com.hoffmann.songify;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
public class SongsController {

    Map<Integer, String> database = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(SongsController.class);

    @GetMapping("/songs")
    public ResponseEntity<SongResponseDto> getAllSongs(@RequestParam (required = false) Integer limit) {
        database.put(1, "Shawn Mendes song");
        database.put(2, "Araia song");
        database.put(3, "Shivers");
        database.put(4, "Bad blood");

        if(limit != null) {
            Map<Integer, String> limitedMap = database.entrySet()
                    .stream()
                    .limit(limit)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            SongResponseDto response  = new SongResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }

        SongResponseDto response = new SongResponseDto(database);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SingleSongReponseDto> getSongById(@PathVariable("id") Integer id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        String song = database.get(id);
        if(song == null) {
            return ResponseEntity.notFound().build();
        }
        SingleSongReponseDto response = new SingleSongReponseDto(song);
        return ResponseEntity.ok(response);
    }
}
