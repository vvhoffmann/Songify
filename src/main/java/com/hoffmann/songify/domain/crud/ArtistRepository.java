package com.hoffmann.songify.domain.crud;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.web.PageableDefault;

import java.util.Optional;
import java.util.Set;

interface ArtistRepository extends Repository<Artist, Long> {

    Artist save(Artist artist);

    Set<Artist> findAll(@PageableDefault(page = 0, size = 12) Pageable pageable);

    Optional<Artist> findById(Long artistId);

    @Modifying
    @Query("delete from Artist a where a.id = :id")
    void deleteById(Long id);
}
