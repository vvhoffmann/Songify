package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.ArtistDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@AllArgsConstructor
class ArtistAdder {
    ArtistRepository artistRepository;

    ArtistDto save(String name) {
        final Artist artist = artistRepository.save(new Artist(name));
        return new ArtistDto(artist.getId(), artist.getName());
    }

    ArtistDto addArtistWithDefaultAlbumAndSong(final String artistName) {
        Artist savedArtist = saveArtistWithDefaultAlbumAndSong(artistName);
        return new ArtistDto(savedArtist.getId(), savedArtist.getName());
    }

    private Artist saveArtistWithDefaultAlbumAndSong(final String artistName) {
        Artist artist = new Artist(artistName);
        Album album = new Album();
        album.setTitle("default album " + UUID.randomUUID());
        album.setReleaseDate(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        Song song = new Song("default-song-name: " + UUID.randomUUID());
        album.addSong(song);
        artist.addAlbum(album);
        return artistRepository.save(artist);
    }
}
