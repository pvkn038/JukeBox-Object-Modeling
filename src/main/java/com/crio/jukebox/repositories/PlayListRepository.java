package com.crio.jukebox.repositories;
import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.PlayList;
import java.util.Map;
import java.util.HashMap;
public class PlayListRepository implements IPlayListRepository{

    private final Map<String,PlayList> PlayListMap;
    private Integer autoIncrement = 0;

    public PlayListRepository()
    {
        PlayListMap = new HashMap<String,PlayList>();
    }

    public PlayListRepository(Map<String,PlayList> playlistmap)
    {
        this.PlayListMap = playlistmap;
    }
    public PlayListRepository(Map<String, PlayList> playlistMap, Integer autoIncrement) {
        this.PlayListMap = playlistMap;
        this.autoIncrement = autoIncrement;
    }
    public PlayList save(PlayList playlist)
    {
        if(playlist.getId() == null)
        {
            autoIncrement++;
            PlayList list = new PlayList(Integer.toString(autoIncrement),playlist.getName(),playlist.getOwner(),playlist.getsongsList(),playlist.getCurrentSongId());
            PlayListMap.put(list.getId(),list);
            return list;
        }
        PlayListMap.put(playlist.getId(),playlist);
        return playlist;
    }
    public List<PlayList> findAll()
    {
        return null;
    }
    public Optional<PlayList> findById(String id)
    {
        return Optional.ofNullable(PlayListMap.get(id));
    }
    public boolean existsById(String id)
    {
        return false;
    }
    public void delete(PlayList entity)
    {
       
    }
    public void deleteById(String id)
    {
        PlayListMap.remove(id);
    }
    public long count()
    {
        return 0;
    }
    
}
