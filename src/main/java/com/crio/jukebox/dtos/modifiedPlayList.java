package com.crio.jukebox.dtos;
import java.util.List;
public class modifiedPlayList {
    private String playListId;
    private String playListName;
    private List<String> songId;

    public modifiedPlayList(){}
    public modifiedPlayList(String playListId,String playListName,List<String> songId)
    {
        this.playListId = playListId;
        this.playListName = playListName;
        this.songId = songId;
    }

    public String getplayListId()
    {
        return playListId;
    }
    public void setplayListId(String playListId)
    {
        this.playListId = playListId;
    }
    public String getPlayListName()
    {
        return playListName;
    }
    public void setPlayListName(String PlayListName)
    {
        this.playListName = PlayListName;
    }

    public List<String> getsongId()
    {
        return songId;
    }
    public void setsongId(List<String> songId)
    {
        this.songId = songId;
    }

}
