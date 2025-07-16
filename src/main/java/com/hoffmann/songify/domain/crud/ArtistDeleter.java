package com.hoffmann.songify.domain.crud;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
class ArtistDeleter {

    private final ArtistRetriever artistRetriever;
    private final AlbumRetriever albumRetriever;

    private final ArtistRepository artistRepository;

    private final SongDeleter songDeleter;
    private final AlbumDeleter albumDeleter;

    void deleteArtistsByIdWithAlbumsAndSongs(final Long artistId) {
        final Artist artist = artistRetriever.findById(artistId);
        final Set<Album> artistAlbums = albumRetriever.findAllAlbumsByArtistId(artistId);
        if (artistAlbums.isEmpty()) {
            log.info("No albums found for artist id {}", artistId);
            artistRepository.deleteById(artistId);
        }

        artistAlbums.stream()
                .filter(album -> album.getArtists().size() >= 2)
                .forEach(album -> album.removeArtist(artist));

        final Set<Album> albumsWithOnlyOneArtist = artistAlbums.stream()
                .filter(album -> album.getArtists().size() == 1)
                .collect(Collectors.toSet());

        final Set<Long> allSongsIdsToRemove = albumsWithOnlyOneArtist.stream()
                .flatMap(album -> album.getSongs().stream())
                .map(Song::getId)
                .collect(Collectors.toSet());

        songDeleter.deleteAllByIds(allSongsIdsToRemove);

        final Set<Long> albumsIds = albumsWithOnlyOneArtist.stream()
                .map(Album::getId)
                .collect(Collectors.toSet());

        albumDeleter.deleteAllByIds(albumsIds);

        artistRepository.deleteById(artistId);
    }
}