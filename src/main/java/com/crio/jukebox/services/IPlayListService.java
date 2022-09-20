package com.crio.jukebox.services;
import java.util.List;
import com.crio.jukebox.dtos.modifiedPlayList;
import com.crio.jukebox.dtos.playingSong;
public interface IPlayListService {
    public String create(String userid,String name,List<String> songId);
    public String delete(String userid,String id);
    public modifiedPlayList addSong(String userId,String id,List<String> songId);
    public modifiedPlayList deleteSong(String userId,String id,List<String> songId);
    public playingSong PlayPlaylist(String userId,String id);
    public playingSong PlayNextSong(String userId,String button);
    public playingSong PlayPreviousSong(String userId,String button);
    public playingSong PlaySpecificSong(String userId,String songId);
}
