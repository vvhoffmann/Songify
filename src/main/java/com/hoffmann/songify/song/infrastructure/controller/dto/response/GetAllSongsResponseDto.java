package com.hoffmann.songify.song.infrastructure.controller.dto.response;

import com.hoffmann.songify.song.domain.model.SongEntity;

import java.util.List;

public record GetAllSongsResponseDto(List<SongEntity> songs) {
}