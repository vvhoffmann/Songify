package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.AlbumDto;
import com.hoffmann.songify.domain.crud.dto.SongRequestDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
@Transactional
class AlbumAdder {

    private final AlbumRepository albumRepository;
    private final SongRetriever songRetriever;
    private final SongRepository songRepository;

    AlbumDto addAlbum(SongRequestDto songRequestDto, String title, Instant releaseDate) {
        Song song = new Song(songRequestDto.name());
        Album album = new Album();
        album.setTitle(title);
        album.setReleaseDate(releaseDate);
        Album savedAlbum = albumRepository.save(album);
        savedAlbum.addSong(song);
        return new AlbumDto(savedAlbum.getId(), savedAlbum.getTitle());
    }

}