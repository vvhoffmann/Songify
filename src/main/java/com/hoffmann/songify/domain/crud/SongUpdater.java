package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.SongDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongUpdater {

    SongRepository songRepository;
    SongRetriever songRetriever;

    SongDto updateById(Long id, SongEntity song) {
        songRetriever.existsById(id);
        return songRepository.updateById(id, song);
    }

    SongDto updatePartiallyById(Long id, SongEntity song) {
        SongEntity oldSong = songRetriever.findSongById(id);
        String newSongName = song.getName();

        SongEntity.SongEntityBuilder songBuilder = SongEntity.builder();

        if (newSongName != null)
            songBuilder.name(newSongName);
        else
            songBuilder.name(oldSong.getName());

        SongEntity songToSave = songBuilder.build();

        return songRepository.updateById(id, songToSave);
    }
}