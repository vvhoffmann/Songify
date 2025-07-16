package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.SongDto;
import com.hoffmann.songify.infrastructure.apivalidation.exception.SongNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongRetriever {

    private final SongRepository songRepository;

    List<SongDto> findAll(Pageable pageable) {
        log.info("retrieving all songs");
        return songRepository.findAll(pageable)
                .stream()
                .map(song -> new SongDto(song.getId(), song.getName()))
                .toList();
    }

    SongDto findSongDtoById(Long id) {
        return songRepository.findById(id)
                .map(song -> new SongDto(
                        song.getId(),
                        song.getName()
                ))
                .orElseThrow(() -> new SongNotFoundException("Song with id " + id + " doesn't exist"));
    }

    SongEntity findSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException("Song with id " + id + " doesn't exist"));
    }

    void existsById(Long id) {
        songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException("Song with id " + id + " doesn't exist"));
    }
}