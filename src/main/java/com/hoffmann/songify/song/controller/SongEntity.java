package com.hoffmann.songify.song.controller;

public record SongEntity (String name, String artist) {
    @Override
    public String toString() {
        return "name='" + name +
                ", artist='" + artist;
    }
}
