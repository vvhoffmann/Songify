package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.AlbumDto;
import com.hoffmann.songify.domain.crud.dto.AlbumRequestDto;
import com.hoffmann.songify.domain.crud.dto.ArtistDto;
import com.hoffmann.songify.domain.crud.dto.ArtistRequestDto;
import com.hoffmann.songify.domain.crud.dto.SongRequestDto;
import com.hoffmann.songify.infrastructure.apivalidation.exception.ArtistNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SongifyCrudFacadeTest {

    SongifyCrudFacade songifyCrudFacade = SongifyCrudFacadeConfiguration.createSongifyCrud(
            new InMemorySongRepository(),
            new InMemoryGenreRepository(),
            new InMemoryArtistRepository(),
            new InMemoryAlbumRepository()
    );

    @Test
    @DisplayName("Should add artist 'Harry Potter' when 'Harry Potter' request was sent")
    void should_add_artist_harry_potter_when_harry_potter_request_was_sent() {
        //given
        final ArtistRequestDto artistRequestDto = ArtistRequestDto.builder()
                .name("Harry Potter")
                .build();
        final Set<ArtistDto> allArtists = songifyCrudFacade.findAllArtists(Pageable.unpaged());
        assertTrue(allArtists.isEmpty());
        //when
        final ArtistDto artistDto = songifyCrudFacade.addArtist(artistRequestDto);
        //then
        assertThat(artistDto.id()).isEqualTo(0L);
        assertThat(artistDto.name()).isEqualTo("Harry Potter");
        int size = songifyCrudFacade.findAllArtists(Pageable.unpaged()).size();
        assertThat(size).isEqualTo(1);
    }

    @Test
    @DisplayName("Should throw exception artist not found when id was one")
    void should_throw_exception_artist_not_found_when_id_was_one() {
        //given
        final Long id = 1L;
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged()).isEmpty());
        //when
        final Throwable throwable = catchThrowable(() -> songifyCrudFacade.deleteArtistByIdWithSongsAndAlbums(id));
        //then
        assertThat(throwable).isInstanceOf(ArtistNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Artist with id 1 not found");
    }

    @Test
    @DisplayName("Should delete artist by id When he have no albums")
    public void should_delete_artist_by_id_when_he_have_no_albums() {
        //given
        ArtistRequestDto artistRequestDto = ArtistRequestDto.builder()
                .name("shawn mendes")
                .build();
        final ArtistDto artistDto = songifyCrudFacade.addArtist(artistRequestDto);
        Long artistId = artistDto.id();
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).isEmpty();
        //when
        songifyCrudFacade.deleteArtistByIdWithSongsAndAlbums(artistId);
        //then
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged()).isEmpty());
    }

    @Test
    public void should_delete_artist_by_id_when_he_have_one_album() {
        //given
        ArtistRequestDto artistRequestDto = ArtistRequestDto.builder()
                .name("shawn mendes")
                .build();
        Long artistId = songifyCrudFacade.addArtist(artistRequestDto).id();
        SongRequestDto songRequestDto = SongRequestDto.builder()
                        .name("Shivers")
                        .build();
        AlbumRequestDto albumRequestDto = AlbumRequestDto.builder()
                        .title("Best of")
                        .releaseDate(Instant.now())
                        .build();
        final AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(albumRequestDto, songRequestDto);
        songifyCrudFacade.addArtistToAlbum(artistId,albumDto.id());
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).size().isEqualTo(1);
        //when
        songifyCrudFacade.deleteArtistByIdWithSongsAndAlbums(artistId);
        //then
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged()).isEmpty());
        assertThat(songifyCrudFacade.findAllAlbums().isEmpty());
        assertThat(songifyCrudFacade.findAllSongs(Pageable.unpaged()).isEmpty());
    }

    @Test
    @DisplayName("Should delete only artist from album by id When there were more than 1 artist in album")
    public void should_delete_only_artist_from_album_by_when_there_were_more_than_one_artist_in_album() {
//        assertThat(songifyCrudFacade.findAlbumByIdWithArtistsAndSongs(albumId)
//                .getArtists()
//                .size()).isGreaterThanOrEqualTo(2);
    }
}