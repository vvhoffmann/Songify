package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.SongDto;
import org.springframework.stereotype.Service;

@Service
class SongDomainMapper {
    public static SongDto mapFromSongEntityToSongDto(Song song) {
        return new SongDto(song.getId(), song.getName());
    }
}
