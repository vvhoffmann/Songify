package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.AlbumDto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class InMemoryAlbumRepository implements AlbumRepository {

    Map<Long, Album> db = new HashMap<>();
    AtomicInteger index = new AtomicInteger(0);

    @Override
    public Optional<Album> findById(final Long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public Optional<Album> findAlbumByIdWithSongsAndArtists(final Long id) {
        return Optional.empty();
    }

    @Override
    public Set<Album> findAllByArtistId(final Long id) {
        return db.values().stream()
                .filter(album -> album.getArtists().stream()
                        .anyMatch(a -> a.getId().equals(id)))
                .collect(Collectors.toSet());
    }

    @Override
    public int deleteByIds(final Collection<Long> ids) {
        return 0;
    }

    @Override
    public Set<AlbumDto> findAll() {
        return db.values().stream()
                .map(album -> new AlbumDto(album.getId(), album.getTitle()))
                .collect(Collectors.toSet());
    }

    @Override
    public Album save(final Album album) {
        long index = this.index.getAndIncrement();
        db.put(index, album);
        album.setId(index);
        return album;
    }
}