package com.hoffmann.songify.song.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@Table(name ="song")
public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String artist;

    public SongEntity() {}

    public SongEntity(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public SongEntity(Long id, String name, String artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "name='" + name +
                ", artist='" + artist;
    }
}
