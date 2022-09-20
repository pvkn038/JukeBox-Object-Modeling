package com.crio.jukebox.entities;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;;

public class PlayList {
    private  String id;
    private  String name;
    private User Owner;
    private  List<Song> songsList;
    private String currentSongId;

   public PlayList(){}

   public PlayList(String id,String name,User Owner,List<Song> songsList,String currentSongsId)
   {
        this.id = id;
        this.name = name;
        this.Owner = Owner;
        this.songsList = songsList;
        this.currentSongId = currentSongsId;
   }
   public PlayList(String name,User Owner,List<Song> songsList,String currentSongsId)
   {
        this.name = name;
        this.Owner = Owner;
        this.songsList = songsList;
        this.currentSongId = currentSongsId;
   }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public User getOwner()
    {
        return Owner;
    }
    public void setOwner(User Owner)
    {
        this.Owner = Owner;
    }
    public List<Song> getsongsList()
    {
        return songsList;
    }
    public void setsongsList(List<Song> songsList)
    {
        this.songsList = songsList;
    }
    public String getCurrentSongId()
    {
        return currentSongId;
    }
    public void setCurrentSongId(String currentsongId)
    {
        this.currentSongId = currentsongId;
    }



}
