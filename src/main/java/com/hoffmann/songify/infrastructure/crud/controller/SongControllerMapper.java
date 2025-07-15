package com.hoffmann.songify.infrastructure.crud.controller;

import com.hoffmann.songify.domain.crud.dto.SongDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.request.CreateSongRequestDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.request.UpdateSongRequestDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.CreateSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.DeleteSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.GetAllSongsResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.GetSingleSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.PartiallyUpdateSongResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.SongControllerResponseDto;
import com.hoffmann.songify.infrastructure.crud.controller.dto.response.UpdateSongResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

class SongControllerMapper {

    public static GetAllSongsResponseDto mapFromSongDtoToGetAllSongsResponseDto(List<SongDto> allSongs) {
        List<SongControllerResponseDto> songControllerResponseDtos = allSongs.stream()
                .map(songDto -> SongControllerResponseDto.builder()
                        .name(songDto.name())
                        .artist(songDto.artist())
                        .build())
                .collect(Collectors.toList());
        return new GetAllSongsResponseDto(songControllerResponseDtos);
    }

    public static GetSingleSongResponseDto mapSongDtoToGetSingleSongResponseDto(SongDto song) {
        SongControllerResponseDto songControllerResponseDto = new SongControllerResponseDto(song.name(), song.artist());
        return new GetSingleSongResponseDto(songControllerResponseDto);
    }

    public static CreateSongResponseDto mapFromSongDtoToCreateSongResponseDto(SongDto song) {
        SongControllerResponseDto songControllerResponseDto = SongControllerMapper.mapFromSongDtoToSongControllerResponseDto(song);
        return new CreateSongResponseDto(songControllerResponseDto);
    }

    private static SongControllerResponseDto mapFromSongDtoToSongControllerResponseDto(final SongDto song) {
        return new SongControllerResponseDto(song.name(), song.artist());
    }

    public static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Long id) {
        return new DeleteSongResponseDto("You deleted song with id " + id, HttpStatus.OK);
    }

    public static UpdateSongResponseDto mapFromSongDtoToUpdateSongResponseDto(SongDto song) {
        SongControllerResponseDto songControllerResponseDto = new SongControllerResponseDto(song.name(), song.artist());
        return new UpdateSongResponseDto(songControllerResponseDto.name(), songControllerResponseDto.artist());
    }

    public static PartiallyUpdateSongResponseDto mapFromSongToPartiallyUpdateSongDto(SongDto updatedSong) {
        SongControllerResponseDto songControllerResponseDto = new SongControllerResponseDto(updatedSong.name(), updatedSong.artist());
        return new PartiallyUpdateSongResponseDto(songControllerResponseDto);
    }

    static SongDto mapFromUpdateSongRequestDtoToSongDto(final @Valid UpdateSongRequestDto request) {
        return new SongDto(request.song(), request.artist());
    }

    static SongDto mapFromPartiallyUpdateSongRequestDtoToSongDto(final PartiallyUpdateSongRequestDto request) {
        return new SongDto(request.song(), request.artist());
    }

    static SongDto mapFromCreateSongRequestDtoToSongDto(final @Valid CreateSongRequestDto request) {
        return new SongDto(request.song(), request.artist());
    }
}