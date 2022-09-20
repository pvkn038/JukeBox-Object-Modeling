package com.crio.jukebox.services;
import com.crio.jukebox.entities.Song;
import java.util.Optional;
public interface ISongService {
    public String loadDate(String fileName);
    public Optional<Song>  getSong(String id);
}
