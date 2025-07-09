package com.hoffmann.songify.song.domain.service;

import com.hoffmann.songify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service@Log4j2
public class SongDeleter {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

    public SongDeleter(SongRepository songRepository, SongRetriever songRetriever) {
        this.songRepository = songRepository;
        this.songRetriever = songRetriever;
    }

    public void deleteById(Long id) {
        songRetriever.existsById(id);
        songRepository.deleteById(id);
    }
}
