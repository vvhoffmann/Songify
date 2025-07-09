package com.hoffmann.songify.song.domain.service;

import com.hoffmann.songify.song.domain.model.SongEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SongUpdater {

    SongRetriever songRetriever;

    public SongUpdater(SongRetriever songRetriever) {
        this.songRetriever = songRetriever;
    }

    public void updateById(Long id, SongEntity song) {
        SongEntity databaseSong = songRetriever.findSongById(id);

        databaseSong.setName(song.getName());
        databaseSong.setArtist(song.getArtist());
    }

    public SongEntity updatePartiallyById(Long id, SongEntity song) {
        SongEntity databaseSong = songRetriever.findSongById(id);
        String newArtistName = song.getArtist();
        String newSongName = song.getName();

        if(newSongName != null)
            databaseSong.setName(newSongName);
        if(newArtistName != null)
            databaseSong.setArtist(newArtistName);

        return databaseSong;
    }
}