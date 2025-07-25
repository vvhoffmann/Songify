package com.hoffmann.songify.domain.crud;

import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

class InMemoryArtistRepository implements ArtistRepository {

    Map<Long, Artist> db = new HashMap<>();
    AtomicInteger index = new AtomicInteger(0);

    @Override
    public void deleteById(final Long id) {
        findById(id).ifPresent(db::remove);
    }

    @Override
    public Artist save(final Artist artist) {
        long index = this.index.getAndIncrement();
        db.put(index, artist);
        artist.setId(index);
        return artist;
    }

    @Override
    public Set<Artist> findAll(final Pageable pageable) {
        return new HashSet<>(db.values());
    }

    @Override
    public Optional<Artist> findById(final Long artistId) {
        final Artist artist = db.get(artistId);
        return Optional.ofNullable(artist);
    }
}