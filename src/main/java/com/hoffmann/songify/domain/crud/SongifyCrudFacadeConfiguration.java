package com.hoffmann.songify.domain.crud;

class SongifyCrudFacadeConfiguration {

    public static SongifyCrudFacade createSongifyCrud(final SongRepository songRepository,
                                                      final GenreRepository genreRepository,
                                                      final ArtistRepository artistRepository,
                                                      final AlbumRepository albumRepository){
        SongRetriever songRetriever = new SongRetriever(songRepository);
        SongUpdater songUpdater = new SongUpdater(songRepository,songRetriever);
        AlbumAdder albumAdder = new AlbumAdder(albumRepository,songRetriever, songRepository);
        ArtistRetriever artistRetriever = new ArtistRetriever(artistRepository);
        AlbumRetriever albumRetriever = new AlbumRetriever(albumRepository);
        SongDeleter songDeleter = new SongDeleter(songRepository, songRetriever);
        SongAdder songAdder = new SongAdder(songRepository);
        ArtistAdder artistAdder = new ArtistAdder(artistRepository);
        GenreAdder genreAdder = new GenreAdder(genreRepository);
        AlbumDeleter albumDeleter = new AlbumDeleter(albumRepository);
        ArtistDeleter artistDeleter = new ArtistDeleter(artistRepository, artistRetriever, albumRetriever, albumDeleter, songDeleter);
        ArtistAssigner artistAssigner = new ArtistAssigner(artistRetriever, albumRetriever);
        ArtistUpdater artistUpdater = new ArtistUpdater(artistRetriever);
        return new SongifyCrudFacade(
                songAdder,
                songRetriever,
                songUpdater,
                songDeleter,
                artistAdder,
                artistRetriever,
                artistAssigner,
                artistUpdater,
                artistDeleter,
                genreAdder,
                albumAdder,
                albumRetriever
        );
    }
}