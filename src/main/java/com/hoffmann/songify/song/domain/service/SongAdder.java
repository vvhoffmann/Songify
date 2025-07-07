package com.hoffmann.songify.song.domain.service;

import com.hoffmann.songify.song.domain.model.SongEntity;
import com.hoffmann.songify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SongAdder {

    private final SongRepository songRepository;

    public SongAdder(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public SongEntity addSong(SongEntity song) {
        log.info("added new song: " + song);

        songRepository.saveToDatabase(song);

        return song;
    }
}