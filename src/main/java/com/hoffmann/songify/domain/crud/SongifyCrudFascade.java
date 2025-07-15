package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.AlbumDto;
import com.hoffmann.songify.domain.crud.dto.AlbumRequestDto;
import com.hoffmann.songify.domain.crud.dto.ArtistDto;
import com.hoffmann.songify.domain.crud.dto.ArtistRequestDto;
import com.hoffmann.songify.domain.crud.dto.GenreDto;
import com.hoffmann.songify.domain.crud.dto.GenreRequestDto;
import com.hoffmann.songify.domain.crud.dto.SongDto;
import com.hoffmann.songify.domain.crud.dto.SongRequestDto;
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
                .map(song -> new SongDto(song.getId() ,song.getName()))
                .toList();
    }

    public SongDto findSongById(Long id) {
        SongEntity song = songRetriever.findSongById(id);
        return SongDomainMapper.mapFromSongEntityToSongDto(song);
    }

    public SongDto saveSongWithArtist(SongRequestDto songRequestDto) {
        return songAdder.save(songRequestDto);
    }

    public void deleteById(Long id) {
        songDeleter.deleteById(id);
    }

    public SongDto updateById(Long id, SongRequestDto newSong) {
        SongLanguage songLanguage = SongLanguage.valueOf(newSong.language().name());
        SongEntity song = new SongEntity(newSong.name(), newSong.releaseDate(), newSong.duration(), songLanguage);
        return songUpdater.updateById(id, song);
    }

    public SongDto updatePartiallyById(Long id, SongRequestDto newSong) {
        SongLanguage songLanguage = SongLanguage.valueOf(newSong.language().name());
        SongEntity song = new SongEntity(newSong.name(), newSong.releaseDate(), newSong.duration(), songLanguage);
        return songUpdater.updatePartiallyById(id, song);
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