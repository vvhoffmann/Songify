package com.hoffmann.songify.domain.crud.song;

import com.hoffmann.songify.domain.crud.song.dto.SongDto;
import com.hoffmann.songify.infrastructure.crud.song.controller.dto.request.CreateSongRequestDto;
import com.hoffmann.songify.infrastructure.crud.song.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.hoffmann.songify.infrastructure.crud.song.controller.dto.request.UpdateSongRequestDto;
import org.springframework.stereotype.Service;

@Service
class SongDomainMapper {
    public static SongDto mapFromSongEntityToSongDto(SongEntity songEntity) {
        return new SongDto(songEntity.getName(), songEntity.getArtist());
    }
}
