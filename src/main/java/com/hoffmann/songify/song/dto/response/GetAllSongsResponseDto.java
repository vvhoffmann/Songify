package com.hoffmann.songify.song.dto.response;

import com.hoffmann.songify.song.controller.SongEntity;

import java.util.Map;

public record GetAllSongsResponseDto(Map<Integer, SongEntity> songs) {
}