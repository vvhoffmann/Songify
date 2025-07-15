package com.hoffmann.songify.infrastructure.crud.controller;

import com.hoffmann.songify.domain.crud.dto.SongDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.CreateSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.DeleteSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.GetAllSongsResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.GetSingleSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.PartiallyUpdateSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.SongControllerResponseDto;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

class SongControllerMapper {

    public static GetAllSongsResponseDto mapFromSongDtoToGetAllSongsResponseDto(List<SongDto> allSongs) {
        List<SongControllerResponseDto> songControllerResponseDtos = allSongs.stream()
                .map(songDto -> SongControllerResponseDto.builder()
                        .name(songDto.name())
                        .build())
                .collect(Collectors.toList());
        return new GetAllSongsResponseDto(songControllerResponseDtos);
    }

    public static GetSingleSongResponseDto mapSongDtoToGetSingleSongResponseDto(SongDto song) {
        SongControllerResponseDto songControllerResponseDto = new SongControllerResponseDto(song.name());
        return new GetSingleSongResponseDto(songControllerResponseDto);
    }

    public static CreateSongResponseDto mapFromSongDtoToCreateSongResponseDto(SongDto song) {
        SongControllerResponseDto songControllerResponseDto = SongControllerMapper.mapFromSongDtoToSongControllerResponseDto(song);
        return new CreateSongResponseDto(songControllerResponseDto);
    }

    private static SongControllerResponseDto mapFromSongDtoToSongControllerResponseDto(final SongDto song) {
        return new SongControllerResponseDto(song.name());
    }

    public static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Long id) {
        return new DeleteSongResponseDto("You deleted song with id " + id, HttpStatus.OK);
    }

    public static PartiallyUpdateSongResponseDto mapFromSongToPartiallyUpdateSongDto(SongDto updatedSong) {
        SongControllerResponseDto songControllerResponseDto = new SongControllerResponseDto(updatedSong.name());
        return new PartiallyUpdateSongResponseDto(songControllerResponseDto);
    }
}