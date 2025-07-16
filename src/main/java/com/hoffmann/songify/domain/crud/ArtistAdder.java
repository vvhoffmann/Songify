package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.ArtistDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ArtistAdder {
    ArtistRepository artistRepository;

    ArtistDto save(String name) {
        final Artist artist = artistRepository.save(new Artist(name));
        return new ArtistDto(artist.getId(), artist.getName());
    }


}
