package com.hoffmann.songify.song.domain.service;

import com.hoffmann.songify.song.domain.model.SongEntity;
import com.hoffmann.songify.song.domain.model.SongNotFoundException;
import com.hoffmann.songify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class SongRetriever {

    private final SongRepository songRepository;

    SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<SongEntity> findAll()
    {
        log.info("retrieving all songs");
        return songRepository.findAll();
    }

    public List<SongEntity> findAllLimitedBy(Integer limit){

        return songRepository.findAll()
                .stream()
                .limit(limit)
                .toList();
    }

    public SongEntity findSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException("Song with id " + id + " doesn't exist"));
    }

    public void existsById(Long id) {
        songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException("Song with id " + id + " doesn't exist"));
    }
}