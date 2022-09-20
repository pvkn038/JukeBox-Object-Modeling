package com.crio.jukebox.services;

import com.crio.jukebox.repositories.ISongRepository;
import java.util.Optional;
import com.crio.jukebox.entities.Song;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SongService implements ISongService {
    ISongRepository songRepository;
    SongService(ISongRepository songRepository)
    {
        this.songRepository = songRepository;
    }
    public String loadDate(String fileName)
    {
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {

                String[] attributes = line.split(",");

                String name = attributes[0];
                String genre = attributes[1];
                String albumName = attributes[2];
                String mainArtist = attributes[3];
                String otherArtist = attributes[4];

                String[] otherArtistArray = otherArtist.split("#");
                List<String> otherArtistList = new ArrayList<>();

                for (int i = 0; i < otherArtistArray.length; i++) {
                    otherArtistList.add(otherArtistArray[i]);
                }
                Song song = new Song(name, genre, albumName, mainArtist, otherArtistList);
                Song returnedSong = songRepository.save(song);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return "Songs Loaded successfully";
    }
    public Optional<Song>  getSong(String id)
    {
        return songRepository.findById(id);
    }
}
