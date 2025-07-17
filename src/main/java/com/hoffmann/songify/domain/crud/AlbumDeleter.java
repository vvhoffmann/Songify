package com.hoffmann.songify.domain.crud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
class AlbumDeleter {

    private final AlbumRepository albumRepository;

    void deleteAllByIds(final Set<Long> albumsIds) {
        albumRepository.deleteByIds(albumsIds);
    }
}