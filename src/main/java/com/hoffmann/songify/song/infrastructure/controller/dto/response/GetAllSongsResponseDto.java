package com.hoffmann.songify.song.infrastructure.controller.dto.response;

import com.hoffmann.songify.song.domain.model.SongEntity;

import java.util.Map;

public record GetAllSongsResponseDto(Map<Integer, SongEntity> songs) {
}