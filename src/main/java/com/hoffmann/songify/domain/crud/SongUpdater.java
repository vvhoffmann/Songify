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

    SongDto updateById(Long id, Song song) {
        songRetriever.existsById(id);
        return songRepository.updateById(id, song);
    }

    SongDto updatePartiallyById(Long id, Song song) {
        Song oldSong = songRetriever.findSongById(id);
        String newSongName = song.getName();

        Song.SongBuilder songBuilder = Song.builder();

        if (newSongName != null)
            songBuilder.name(newSongName);
        else
            songBuilder.name(oldSong.getName());

        Song songToSave = songBuilder.build();

        return songRepository.updateById(id, songToSave);
    }
}