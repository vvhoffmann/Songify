package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.AlbumDto;
import com.hoffmann.songify.domain.crud.dto.AlbumRequestDto;
import com.hoffmann.songify.domain.crud.dto.ArtistDto;
import com.hoffmann.songify.domain.crud.dto.ArtistRequestDto;
import com.hoffmann.songify.domain.crud.dto.GenreDto;
import com.hoffmann.songify.domain.crud.dto.GenreRequestDto;
import com.hoffmann.songify.domain.crud.dto.SongDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
public class SongifyCrudFascade {
    private final SongAdder songAdder;
    private final SongRetriever songRetriever;
    private final SongDeleter songDeleter;
    private final SongUpdater songUpdater;

    private final ArtistAdder artistAdder;
    private final GenreAdder genreAdder;
    private final AlbumAdder albumAdder;

    public List<SongDto> findAll(Pageable pageable) {
        return songRetriever.findAll(pageable)
                .stream()
                .map(song -> new SongDto(song.getName()))
                .toList();
    }

    public SongDto findSongById(Long id) {
        SongEntity song = songRetriever.findSongById(id);
        return SongDomainMapper.mapFromSongEntityToSongDto(song);
    }

    public void save(SongDto songToSave) {
        SongEntity song = new SongEntity(songToSave.name());
        songAdder.save(song);
    }

    public void deleteById(Long id) {
        songDeleter.deleteById(id);
    }

    public void updateById(Long id, SongDto newSong) {
        SongEntity song = new SongEntity(newSong.name());
        songUpdater.updateById(id, song);
    }

    public SongDto updatePartiallyById(Long id, SongDto newSong) {
        SongEntity song = new SongEntity(newSong.name());
        SongEntity updatedSong = songUpdater.updatePartiallyById(id, song);
        return SongDomainMapper.mapFromSongEntityToSongDto(updatedSong);
    }

    public ArtistDto addArtist(ArtistRequestDto artistToAdd)
    {
        return artistAdder.save(artistToAdd.name());
    }

    public GenreDto addGenre(GenreRequestDto genreRequestDto)
    {
        return genreAdder.addGenre(genreRequestDto.name());
    }

    public AlbumDto addAlbumWithSong(AlbumRequestDto albumRequestDto)
    {
        return albumAdder.addAlbum(albumRequestDto.songId(), albumRequestDto.title(), albumRequestDto.releaseDate());
    }
}