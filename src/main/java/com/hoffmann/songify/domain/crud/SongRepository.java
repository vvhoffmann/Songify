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

interface SongRepository extends Repository<SongEntity, Long> {

    SongEntity save(SongEntity song);

    @Query("SELECT s FROM SongEntity s")
    List<SongEntity> findAll(@PageableDefault(page = 0, size = 12) Pageable pageable);

    @Query("SELECT s FROM SongEntity s WHERE s.id = :id")
    Optional<SongEntity> findById(Long id);

    @Modifying
    @Query("DELETE FROM SongEntity s WHERE s.id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE SongEntity s SET s.name = :#{#newSong.name} WHERE s.id = :id")
    SongDto updateById(Long id, SongEntity newSong);

    @Query("SELECT s FROM SongEntity s WHERE s.genre = :genreId")
    List<SongEntity> findAllSongsByGenreId(Long genreId);

    @Modifying
    @Query("delete from SongEntity s where s.id in :ids")
    int deleteByIds(Collection<Long> ids);
}