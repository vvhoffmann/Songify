package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.AlbumDto;
import com.hoffmann.songify.domain.crud.dto.AlbumRequestDto;
import com.hoffmann.songify.domain.crud.dto.AlbumWithArtistsAndSongsDto;
import com.hoffmann.songify.domain.crud.dto.ArtistDto;
import com.hoffmann.songify.domain.crud.dto.ArtistRequestDto;
import com.hoffmann.songify.domain.crud.dto.GenreDto;
import com.hoffmann.songify.domain.crud.dto.GenreRequestDto;
import com.hoffmann.songify.domain.crud.dto.SongDto;
import com.hoffmann.songify.domain.crud.dto.SongRequestDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@Transactional
public class SongifyCrudFascade {
    private final SongAdder songAdder;
    private final SongRetriever songRetriever;
    private final SongDeleter songDeleter;
    private final SongUpdater songUpdater;

    private final ArtistAdder artistAdder;
    private final ArtistRetriever artistRetriever;
    private final ArtistDeleter artistDeleter;

    private final GenreAdder genreAdder;

    private final AlbumAdder albumAdder;
    private final AlbumRetriever albumRetriever;

    public List<SongDto> findAllSongs(Pageable pageable) {
        return songRetriever.findAll(pageable);
    }

    public Set<ArtistDto> findAllArtists(Pageable pageable) {
        return artistRetriever.findAll(pageable);
    }

    public SongDto findSongById(Long id) {
        return songRetriever.findSongDtoById(id);
    }

    public AlbumWithArtistsAndSongsDto findAlbumByIdWithArtistsAndSongs(Long id) {
        return albumRetriever.findAlbumByIdWithArtistsAndSongs(id);
    }

    public SongDto saveSongWithArtist(SongRequestDto songRequestDto) {
        return songAdder.save(songRequestDto);
    }

    public void deleteSongById(Long id) {
        songDeleter.deleteById(id);
    }

    public SongDto updateSongById(Long id, SongRequestDto newSong) {
        SongLanguage songLanguage = SongLanguage.valueOf(newSong.language().name());
        SongEntity song = new SongEntity(newSong.name(), newSong.releaseDate(), newSong.duration(), songLanguage);
        return songUpdater.updateById(id, song);
    }

    public SongDto updatePartiallySongById(Long id, SongRequestDto newSong) {
        SongLanguage songLanguage = SongLanguage.valueOf(newSong.language().name());
        SongEntity song = new SongEntity(newSong.name(), newSong.releaseDate(), newSong.duration(), songLanguage);
        return songUpdater.updatePartiallyById(id, song);
    }

    public ArtistDto addArtist(ArtistRequestDto artistToAdd) {
        return artistAdder.save(artistToAdd.name());
    }

    public GenreDto addGenre(GenreRequestDto genreRequestDto) {
        return genreAdder.addGenre(genreRequestDto.name());
    }

    public AlbumDto addAlbumWithSong(AlbumRequestDto albumRequestDto) {
        return albumAdder.addAlbum(albumRequestDto.songId(), albumRequestDto.title(), albumRequestDto.releaseDate());
    }

    public void deleteArtistByIdWithSongsAndAlbums(final Long id) {
        artistDeleter.deleteArtistsByIdWithAlbumsAndSongs(id);
    }
}