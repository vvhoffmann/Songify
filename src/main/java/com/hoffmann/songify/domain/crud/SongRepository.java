package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.SongDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.web.PageableDefault;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

interface SongRepository extends Repository<Song, Long> {

    Song save(Song song);

    @Query("SELECT s FROM Song s")
    List<Song> findAll(@PageableDefault(page = 0, size = 12) Pageable pageable);

    @Query("SELECT s FROM Song s WHERE s.id = :id")
    Optional<Song> findById(Long id);

    @Modifying
    @Query("DELETE FROM Song s WHERE s.id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Song s SET s.name = :#{#newSong.name} WHERE s.id = :id")
    SongDto updateById(Long id, Song newSong);

    @Query("SELECT s FROM Song s WHERE s.genre = :genreId")
    List<Song> findAllSongsByGenreId(Long genreId);

    @Modifying
    @Query("delete from Song s where s.id in :ids")
    int deleteByIds(Collection<Long> ids);
}