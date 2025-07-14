package com.hoffmann.songify.domain.crud.song;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongUpdater {

    SongRepository songRepository;
    SongRetriever songRetriever;

    void updateById(Long id, SongEntity song) {
        songRetriever.existsById(id);
        songRepository.updateById(id, song);
    }

    SongEntity updatePartiallyById(Long id, SongEntity song) {
        SongEntity oldSong = songRetriever.findSongById(id);
        String newArtistName = song.getArtist();
        String newSongName = song.getName();

        SongEntity.SongEntityBuilder songBuilder = SongEntity.builder();

        if (newSongName != null)
            songBuilder.name(newSongName);
        else
            songBuilder.name(oldSong.getName());

        if (newArtistName != null)
            songBuilder.artist(newArtistName);
        else
            songBuilder.artist(oldSong.getArtist());

        SongEntity songToSave = songBuilder.build();
        songRepository.updateById(id, songToSave);
        return songToSave;
    }
}