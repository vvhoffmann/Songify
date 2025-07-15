package com.hoffmann.songify.domain.crud;

import com.hoffmann.songify.domain.crud.dto.SongDto;
import com.hoffmann.songify.domain.crud.dto.SongLanguageDto;
import com.hoffmann.songify.domain.crud.dto.SongRequestDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongAdder {

    private final SongRepository songRepository;

    SongDto save(SongRequestDto songRequestDto) {
        SongLanguageDto songLanguageDto = songRequestDto.language();
        SongLanguage songLanguage = SongLanguage.valueOf(songLanguageDto.name());
        SongEntity song = new SongEntity(songRequestDto.name(), songRequestDto.releaseDate(), songRequestDto.duration(), songLanguage);
        log.info("added new song: " + song);
        SongEntity savedSong = songRepository.save(song);
        return new SongDto(savedSong.getId(), savedSong.getName());
    }
}