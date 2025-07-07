package com.hoffmann.songify.song.dto.response;

import com.hoffmann.songify.song.controller.SongEntity;

public record SingleSongReponseDto(
        SongEntity song) {
}