package com.crio.jukebox.entities;

import java.util.List;
import java.util.ArrayList;

public class User {
    private  String id;
    private  String name;
    private List<PlayList> playList;
    private PlayList currentPlayList;

    public User(String id,String name,List<PlayList> playList,PlayList currentPlayList)
    {
        this.id = id;
        this.name = name;
        this.playList = playList;
        this.currentPlayList = currentPlayList;
    }
    public User()
    {
        id = "";
        name = "";
        playList = new ArrayList<>();
        currentPlayList = null;
    }
    public User(String name) {
        this.name = name;
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

    public List<PlayList> getplayList()
    {
        return playList;
    }
    public void setPlayList(List<PlayList> list)
    {
        this.playList = list;
    }
    public PlayList getcurrentPlayList()
    {
        return currentPlayList;
    }
    public void setcurrentPlayList(PlayList currentplaylist)
    {
        this.currentPlayList = currentplaylist;
    }
    



}
