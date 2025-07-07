package com.hoffmann.songify.song.infrastructure.controller.dto.response;

import com.hoffmann.songify.song.domain.model.SongEntity;

public record PartiallyUpdateSongResponseDto(SongEntity songEntity) {
}
