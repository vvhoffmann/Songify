package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.AlbumDto;
import com.hoffmann.songify.domain.crud.dto.AlbumWithArtistsAndSongsDto;
import com.hoffmann.songify.domain.crud.dto.ArtistDto;
import com.hoffmann.songify.domain.crud.dto.SongDto;

import com.hoffmann.songify.infrastructure.apivalidation.exception.AlbumNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class AlbumRetriever {

    private final AlbumRepository albumRepository;

    AlbumWithArtistsAndSongsDto findAlbumByIdWithArtistsAndSongs(final Long id) {
        final Album album = albumRepository.findAlbumByIdWithSongsAndArtists(id)
                .orElseThrow(() -> new AlbumNotFoundException("Album with id: " + id + " not found"));

        AlbumDto albumDto = new AlbumDto(album.getId(), album.getTitle());

        final Set<ArtistDto> artistsDto = album.getArtists().stream()
                .map(artist -> new ArtistDto(
                        artist.getId(),
                        artist.getName()
                ))
                .collect(Collectors.toSet());

        final Set<SongDto> songsDto = album.getSongs().stream()
                .map(songEntity -> new SongDto(
                        songEntity.getId(),
                        songEntity.getName()
                ))
                .collect(Collectors.toSet());

        return new AlbumWithArtistsAndSongsDto(
                albumDto,
                artistsDto,
                songsDto
        );
    }

    Set<Album> findAllAlbumsByArtistId(final Long artistId) {
        return albumRepository.findAllByArtistId(artistId);
    }

    Album findById(final Long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() ->
                        new AlbumNotFoundException("Album with id: " + albumId + " not found")
                );
    }
}
