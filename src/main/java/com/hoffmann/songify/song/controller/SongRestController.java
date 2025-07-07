package com.hoffmann.songify.song.controller;

import com.hoffmann.songify.song.dto.request.PartiallyUpdateSongRequestDto;
import com.hoffmann.songify.song.dto.response.PartiallyUpdateSongResponseDto;
import com.hoffmann.songify.song.dto.request.CreateSongRequestDto;
import com.hoffmann.songify.song.dto.request.UpdateSongRequestDto;
import com.hoffmann.songify.song.dto.response.DeleteSongResponseDto;
import com.hoffmann.songify.song.dto.response.SingleSongReponseDto;
import com.hoffmann.songify.song.dto.response.SongResponseDto;
import com.hoffmann.songify.song.dto.response.UpdateSongResponseDto;
import com.hoffmann.songify.song.error.SongNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
public class SongRestController {

    Map<Integer, SongEntity> database = new HashMap<>(Map.of(
            1, new SongEntity("Stiches", "Shawn"),
            2, new SongEntity("One last time", "Ariana"),
            3, new SongEntity("Shivers", "Shawn"),
            4, new SongEntity("no broke boys", "Tekkno Mode")
    ));

    @GetMapping("/songs")
    public ResponseEntity<SongResponseDto> getAllSongs(@RequestParam(required = false) Integer limit) {

        if (limit != null) {
            Map<Integer, SongEntity> limitedMap = database.entrySet()
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
        if (!database.containsKey(id))
            throw new SongNotFoundException("Song with id " + id + " doesn't exist");

        SongEntity song = database.get(id);
        SingleSongReponseDto response = new SingleSongReponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/songs")
    public ResponseEntity<SingleSongReponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request)
    {
        SongEntity song = new SongEntity(request.songName(), request.artistName());
        database.put(database.size() + 1, song);
        log.info("added new song: " + song);
        return ResponseEntity.ok(new SingleSongReponseDto(song));
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Integer id)
    {
        if(!database.containsKey(id))
            throw  new SongNotFoundException("Song with id " + id + " doesn't exist");
        //return new ErrorDeleteSongResponseDto("meggage", HttpStatus.BAD_REQUEST);
        
        database.remove(id);
        return ResponseEntity.ok(new DeleteSongResponseDto("You deleted song with id " + id, HttpStatus.OK));
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<UpdateSongResponseDto> updateSong(@PathVariable Integer id,
                                                        @RequestBody @Valid UpdateSongRequestDto request)
    {
        if(!database.containsKey(id))
            throw  new SongNotFoundException("Song with id " + id + " doesn't exist");
        String newSongName = request.songName();
        String newArtistName = request.artistName();
        SongEntity newSong = new SongEntity(newSongName, newArtistName);
        SongEntity oldSong = database.put(id, newSong);

        log.info("updated song with id: " + id + " with old song " + oldSong + " to " + newSong);
        return ResponseEntity.ok(new UpdateSongResponseDto(newSongName, newArtistName));
    }


    @PatchMapping("/songs/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> updateSongPartially(  @PathVariable Integer id,
                                                                                @RequestBody PartiallyUpdateSongRequestDto request)
    {
        if(!database.containsKey(id))
            throw  new SongNotFoundException("Song with id " + id + " doesn't exist");

        SongEntity oldSong = database.get(id);
        SongEntity.SongEntityBuilder songBuilder = SongEntity.builder();

        String newArtistName = request.artistName();
        String newSongName = request.songName();

        if(newSongName != null)
            songBuilder.name(newSongName);
        else
            songBuilder.name(oldSong.name());

        if(newArtistName != null)
            songBuilder.artist(newArtistName);
        else
            songBuilder.artist(oldSong.artist());

        SongEntity updatedSong = songBuilder.build();
        database.put(id, updatedSong);

        log.info("updated song with id: " + id + " with old song " + oldSong + " to " + updatedSong);
        return ResponseEntity.ok(new PartiallyUpdateSongResponseDto(updatedSong));
    }
}