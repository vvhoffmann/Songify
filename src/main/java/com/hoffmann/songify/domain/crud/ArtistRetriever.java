package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.ArtistDto;
import com.hoffmann.songify.infrastructure.apivalidation.exception.ArtistNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class ArtistRetriever {

    private final ArtistRepository artistRepository;

    Set<ArtistDto> findAll(Pageable pageable) {
        return artistRepository.findAll(pageable)
                .stream()
                .map(artist -> new ArtistDto(
                        artist.getId(),
                        artist.getName()
                ))
                .collect(Collectors.toSet());
    }

    Artist findById(final Long artistId) {
        return artistRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException("Artist not found"));
    }
}