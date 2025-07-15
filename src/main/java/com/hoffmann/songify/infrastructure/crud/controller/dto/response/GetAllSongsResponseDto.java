package com.hoffmann.songify.infrastructure.crud.controller.dto.response;

import java.util.List;

public record GetAllSongsResponseDto(List<SongControllerResponseDto> songs) {
}