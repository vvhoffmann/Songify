package com.hoffmann.songify.song.domain.service;

import com.hoffmann.songify.song.domain.model.SongEntity;
import com.hoffmann.songify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class SongRetriever {

    private final SongRepository songRepository;

    SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Map<Integer,SongEntity> findAll()
    {
        log.info("retrieving all songs");
        Map<Integer, SongEntity> songs = songRepository.findAll();
        return songs;
    }

    public Map<Integer, SongEntity> findAllLimitedBy(Integer limit){

        return songRepository.findAll().entrySet()
                .stream()
                .limit(limit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
