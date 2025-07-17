package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.AlbumDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

interface AlbumRepository extends Repository<Album, Long> {

    Album save(Album album);

    Optional<Album> findById(Long id);

    @Query("""
            select a from Album a
            join fetch a.songs songs
            join fetch a.artists artists
            where a.id = :id""")
    Optional<Album> findAlbumByIdWithSongsAndArtists(Long id);

    @Query("""
            select a from Album a 
            inner join a.artists artists 
            where artists.id = ?1
            """)
    Set<Album> findAllByArtistId(Long id);

    @Modifying
    @Query("delete from Album a where a.id in :ids")
    int deleteByIds(Collection<Long> ids);

    @Query("SELECT a FROM Album a")
    Set<AlbumDto> findAll();
}
