package com.hoffmann.songify.song.controller;

import lombok.Builder;

@Builder
public record SongEntity (String name, String artist) {
    @Override
    public String toString() {
        return "name='" + name +
                ", artist='" + artist;
    }
}
