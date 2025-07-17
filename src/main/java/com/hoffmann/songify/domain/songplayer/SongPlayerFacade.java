package com.hoffmann.songify.domain.songplayer;

import com.hoffmann.songify.domain.crud.SongifyCrudFacade;
import com.hoffmann.songify.domain.crud.dto.SongDto;

public class SongPlayerFacade {

    private final SongifyCrudFacade songifyCrudFacade;
    private final YoutubeHttpClient youtubeHttpClient;

    SongPlayerFacade(final SongifyCrudFacade songifyCrudFacade, final YoutubeHttpClient youtubeHttpClient) {
        this.songifyCrudFacade = songifyCrudFacade;
        this.youtubeHttpClient = youtubeHttpClient;
    }

    public String playSongWithId(Long id){
        SongDto songDtoById = songifyCrudFacade.findSongById(id);
        String name = songDtoById.name();
        String result = youtubeHttpClient.playSongByName(name);
        if(result.equals("success")){
            return result;
        }
        throw new RuntimeException("some error - result failed");
    }
}