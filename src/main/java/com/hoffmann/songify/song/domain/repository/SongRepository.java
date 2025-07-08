package com.hoffmann.songify.song.domain.repository;

import com.hoffmann.songify.song.domain.model.SongEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SongRepository {

    Map<Integer, SongEntity> database = new HashMap<>(Map.of(
            1, new SongEntity("Stiches", "Shawn"),
            2, new SongEntity("One last time", "Ariana"),
            3, new SongEntity("Shivers", "Shawn"),
            4, new SongEntity("no broke boys", "Tekkno Mode")
    ));

    public void saveToDatabase(SongEntity song) {
        database.put(database.size() + 2, song);
    }

    public Map<Integer, SongEntity> findAll() {
        return database;
    }
}