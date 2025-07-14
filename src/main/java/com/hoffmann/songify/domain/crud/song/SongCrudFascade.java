package com.hoffmann.songify.domain.crud.song;

import com.hoffmann.songify.domain.crud.song.dto.SongDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
public class SongCrudFascade {
    private final SongAdder songAdder;
    private final SongRetriever songRetriever;
    private final SongDeleter songDeleter;
    private final SongUpdater songUpdater;

    public List<SongDto> findAll(final Pageable pageable) {
        return songRetriever.findAll(pageable)
                .stream()
                .map(song -> new SongDto(song.getName(), song.getArtist()))
                .toList();
    }

    public SongDto findSongById(final Long id) {
        SongEntity song = songRetriever.findSongById(id);
        return SongDomainMapper.mapFromSongEntityToSongDto(song);
    }

    public void save(final SongDto songToSave) {
        SongEntity song = new SongEntity(songToSave.name(), songToSave.artist());
        songAdder.save(song);
    }

    public void deleteById(final Long id) {
        songDeleter.deleteById(id);
    }

    public void updateById(final Long id, final SongDto newSong) {
        SongEntity song = new SongEntity(newSong.name(), newSong.artist());
        songUpdater.updateById(id, song);
    }

    public SongDto updatePartiallyById(final Long id, final SongDto newSong) {
        SongEntity song = new SongEntity(newSong.name(), newSong.artist());
        SongEntity updatedSong = songUpdater.updatePartiallyById(id, song);
        return SongDomainMapper.mapFromSongEntityToSongDto(updatedSong);
    }
}
