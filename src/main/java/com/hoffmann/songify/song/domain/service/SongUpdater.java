package com.hoffmann.songify.song.domain.service;

import com.hoffmann.songify.song.domain.model.SongEntity;
import com.hoffmann.songify.song.domain.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SongUpdater {

    SongRepository songRepository;
    SongRetriever songRetriever;

    public SongUpdater(SongRepository songRepository, SongRetriever songRetriever) {
        this.songRepository = songRepository;
        this.songRetriever = songRetriever;
    }

    public void updateById(Long id, SongEntity song) {
        songRetriever.existsById(id);
        songRepository.updateById(id, song);
    }

    public SongEntity updatePartiallyById(Long id, SongEntity song) {
        SongEntity oldSong = songRetriever.findSongById(id);
        String newArtistName = song.getArtist();
        String newSongName = song.getName();

        SongEntity.SongEntityBuilder songBuilder = SongEntity.builder();

        if(newSongName != null)
            songBuilder.name(newSongName);
        else
            songBuilder.name(oldSong.getName());

        if(newArtistName != null)
            songBuilder.artist(newArtistName);
        else
            songBuilder.artist(oldSong.getArtist());

        SongEntity songToSave = songBuilder.build();
        songRepository.updateById(id, songToSave);
        return songToSave;
    }
}