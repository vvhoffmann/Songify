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
public class SongifyCrudFacade {
    private final SongAdder songAdder;
    private final SongRetriever songRetriever;
    private final SongUpdater songUpdater;
    private final SongDeleter songDeleter;

    private final ArtistAdder artistAdder;
    private final ArtistRetriever artistRetriever;
    private final ArtistAssigner artistAssigner;
    private final ArtistUpdater artistUpdater;
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
        Song song = new Song(newSong.name(), newSong.releaseDate(), newSong.duration(), songLanguage);
        return songUpdater.updateById(id, song);
    }

    public SongDto updatePartiallySongById(Long id, SongRequestDto newSong) {
        SongLanguage songLanguage = SongLanguage.valueOf(newSong.language().name());
        Song song = new Song(newSong.name(), newSong.releaseDate(), newSong.duration(), songLanguage);
        return songUpdater.updatePartiallyById(id, song);
    }

    public ArtistDto updateArtistNameById(Long artistId, String name) {
        return artistUpdater.updateArtistNameById(artistId, name);
    }

    public ArtistDto addArtist(ArtistRequestDto artistToAdd) {
        return artistAdder.save(artistToAdd.name());
    }

    public ArtistDto addArtistWithDefaultAlbumAndSong(ArtistRequestDto artistToAdd) {
        return artistAdder.addArtistWithDefaultAlbumAndSong(artistToAdd.name());
    }

    public GenreDto addGenre(GenreRequestDto genreRequestDto) {
        return genreAdder.addGenre(genreRequestDto.name());
    }

    public AlbumDto addAlbumWithSong(AlbumRequestDto albumRequestDto, SongRequestDto songRequestDto) {
        return albumAdder.addAlbum(songRequestDto, albumRequestDto.title(), albumRequestDto.releaseDate());
    }

    public void deleteArtistByIdWithSongsAndAlbums(final Long id) {
        artistDeleter.deleteArtistsByIdWithAlbumsAndSongs(id);
    }

    public void assignArtistToAlbum(final Long artistId, final Long albumId) {
        artistAssigner.addArtistToAlbum(artistId, albumId);
    }

    public Set<AlbumDto> findAlbumsByArtistId(final Long artistId) {
        return albumRetriever.findAlbumsByArtistId(artistId);
    }

    public Set<AlbumDto> findAllAlbums() {
        return albumRetriever.findAll();
    }

    public SongDto addSong(final SongRequestDto songRequestDto) {
        return songAdder.save(songRequestDto);
    }

    public void addArtistToAlbum(final Long artistId, final Long albumId) {
        artistAssigner.addArtistToAlbum(artistId,albumId);
    }
}