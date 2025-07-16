package com.hoffmann.songify.infrastructure.crud.controller;

import com.hoffmann.songify.domain.crud.SongifyCrudFascade;
import com.hoffmann.songify.domain.crud.dto.ArtistDto;
import com.hoffmann.songify.domain.crud.dto.ArtistRequestDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.CreateArtistResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.GetAllArtistsResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.request.ArtistUpdateRequestDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.DeleteArtistResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/artists")
class ArtistRestController {

    private final SongifyCrudFascade songifyCrudFascade;

    @PostMapping("/{name}")
    ResponseEntity<CreateArtistResponseDto> postArtist(@RequestBody ArtistRequestDto artistToAdd) {
        songifyCrudFascade.addArtist(artistToAdd);
        CreateArtistResponseDto body = ArtistControllerMapper.mapFromArtistDtoToCreateArtistResponseDto(artistToAdd);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/{name}/default")
    ResponseEntity<CreateArtistResponseDto> postArtistWithDefaultAlbumAndSong(@RequestBody ArtistRequestDto artistToAdd) {
        songifyCrudFascade.addArtistWithDefaultAlbumAndSong(artistToAdd);
        CreateArtistResponseDto body = ArtistControllerMapper.mapFromArtistDtoToCreateArtistResponseDto(artistToAdd);
        return ResponseEntity.ok(body);
    }

    @GetMapping
    ResponseEntity<GetAllArtistsResponseDto> getArtists(Pageable pageable) {
        final Set<ArtistDto> allArtists = songifyCrudFascade.findAllArtists(pageable);
        GetAllArtistsResponseDto body = new GetAllArtistsResponseDto(allArtists);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{artistId}")
    ResponseEntity<DeleteArtistResponseDto> removeArtistByIdWithSongsAndAlbums(@PathVariable Long artistId) {
        songifyCrudFascade.deleteArtistByIdWithSongsAndAlbums(artistId);
        DeleteArtistResponseDto body = new DeleteArtistResponseDto("deleting artist with id : " + artistId + " succeed", HttpStatus.OK);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{artistId}/{albumId}")
    ResponseEntity<String> assignArtistToAlbumByIds(@PathVariable Long artistId, @PathVariable Long albumId) {
        songifyCrudFascade.assignArtistToAlbum(artistId, albumId);
        return ResponseEntity.ok("assigned artist with it:" + artistId + " to album with id : " + albumId);
    }

    @PutMapping("/{artistId}")
    ResponseEntity<ArtistDto> assignArtistToAlbumByIds(@PathVariable Long artistId, @Valid @RequestBody ArtistUpdateRequestDto requestDto) {
        final ArtistDto artistDto = songifyCrudFascade.updateArtistNameById(artistId, requestDto.newArtistName());

        return ResponseEntity.ok(artistDto);
    }

}