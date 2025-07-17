package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.SongDto;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

class InMemorySongRepository implements SongRepository{

    AtomicInteger index = new AtomicInteger(0);
    Map<Long, Song> db = new HashMap<>();

    @Override
    public List<Song> findAll(final Pageable pageable) {
        return db.values().stream().toList();
    }

    @Override
    public Optional<Song> findById(final Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(final Long id) {

    }

    @Override
    public SongDto updateById(final Long id, final Song newSong) {
        return null;
    }

    @Override
    public List<Song> findAllSongsByGenreId(final Long genreId) {
        return List.of();
    }

    @Override
    public int deleteByIds(final Collection<Long> ids) {
        ids.forEach(id -> db.remove(id));
        return 1;
    }

    @Override
    public Song save(final Song song) {
        return db.put(song.getId(), song);
    }
}