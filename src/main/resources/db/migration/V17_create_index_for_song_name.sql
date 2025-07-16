CREATE INDEX idx_song_name ON song (name);

EXPLAIN ANALYSE SELECT name FROM Song where name='Yesterday';