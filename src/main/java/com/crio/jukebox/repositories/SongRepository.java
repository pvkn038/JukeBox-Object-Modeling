package com.crio.jukebox.repositories;
import java.util.Map;
import java.util.HashMap;
import com.crio.jukebox.entities.Song;
import java.util.Optional;
import java.util.List;
public class SongRepository implements ISongRepository{
    private Map<String,Song> songsListMap;
    private Integer autoIncrement = 0;
    
    public SongRepository()
    {
        this.songsListMap = new HashMap<String,Song>();
    }
    public SongRepository(Map<String,Song> songsmap)
    {
        this.songsListMap = songsmap;
    }
    public SongRepository(Map<String,Song> songsmap,Integer autoIncrement)
    {
        this.songsListMap = songsmap;
        this.autoIncrement = autoIncrement;
    }

    public Song save(Song song)
    {
        if(song.getId() == null)
        {
            autoIncrement++;
            Song list = new Song(Integer.toString(autoIncrement),song.getName(),song.getGenre(),song.getAlbum(),song.getArtistName(),song.getOtherArtistList());
            songsListMap.put(list.getId(),list);
            return list;
        }
        songsListMap.put(song.getId(),song);
        return song;
    }
    public List<Song> findAll()
    {
        return null;
    }
    public Optional<Song> findById(String id)
    {
        return Optional.ofNullable(songsListMap.get(id));
    }
    public boolean existsById(String id)
    {
        return false;
    }
    public void delete(Song entity)
    {
       
    }
    public void deleteById(String id)
    {
        
    }
    public long count()
    {
        return 0;
    }

}
