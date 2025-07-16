package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.ArtistDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ArtistUpdater {
    private final ArtistRetriever artistRetriever;

    ArtistDto updateArtistNameById(Long id, String name) {
        Artist artist = artistRetriever.findById(id);
        artist.setName(name);
        return new ArtistDto(artist.getId(), artist.getName());
    }
}
