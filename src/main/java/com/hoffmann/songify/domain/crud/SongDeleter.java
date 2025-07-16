package com.hoffmann.songify.domain.crud;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Log4j2
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongDeleter {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

    void deleteById(Long id) {
        songRetriever.existsById(id);
        songRepository.deleteById(id);
    }

    void deleteAllByIds(final Set<Long> allSongsIdsToRemove) {
        songRepository.deleteByIds(allSongsIdsToRemove);
    }
}