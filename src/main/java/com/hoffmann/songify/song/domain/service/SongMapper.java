package com.hoffmann.songify.song.domain.service;

import com.hoffmann.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import com.hoffmann.songify.song.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.hoffmann.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.hoffmann.songify.song.infrastructure.controller.dto.response.*;
import com.hoffmann.songify.song.domain.model.SongEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public class SongMapper {

    public static GetAllSongsResponseDto mapSongToGetAllSongsResponseDto(List<SongEntity> database) {
        return new GetAllSongsResponseDto(database);
    }

    public static GetSingleSongResponseDto mapSongToGetSingleSongResponseDto(SongEntity song) {
        return new GetSingleSongResponseDto(song);
    }

    public static SongEntity mapFromCreateSongRequestDtoToSong(CreateSongRequestDto request) {
        return new SongEntity(request.song(), request.artist());
    }

    public static CreateSongResponseDto mapFromSongToCreateSongResponseDto(SongEntity song) {
        return new CreateSongResponseDto(song);
    }

    public static SongEntity mapUpdateSongRequestDtoToSong(UpdateSongRequestDto request) {
        return new SongEntity(request.song(), request.artist());
    }

    public static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Long id) {
        return new DeleteSongResponseDto("You deleted song with id " + id, HttpStatus.OK);
    }

    public static UpdateSongResponseDto mapSongToUpdateSongResponseDto(SongEntity newSong) {
        return new UpdateSongResponseDto(newSong.getName(), newSong.getArtist());
    }

    public static SongEntity mapPartiallyUpdateSongRequestDtoToSong(PartiallyUpdateSongRequestDto request) {
        return new SongEntity(request.song(), request.artist());
    }

    public static PartiallyUpdateSongResponseDto mapSongToPartiallyUpdateSongDto(SongEntity updatedSong) {
        return new PartiallyUpdateSongResponseDto(updatedSong);
    }
}