package com.crio.jukebox.entities;


import java.util.List;
public class Song {
    private String id;
    private String name;
    private String genre;
    private String album;
    private String artistName;
    private List<String> otherartistsList;
    
    public Song(){}
    public Song(String id,String name,String genre,String album,String artistName,List<String> otherartistsList){
        this.id = id;
        this.name = name;
        this.album = album;
        this.artistName = artistName;
        this.otherartistsList = otherartistsList;
    }
    public Song(String name,String genre,String album,String artistName,List<String> otherartistsList)
    {
        this.name = name;
        this.album = album;
        this.artistName = artistName;
        this.otherartistsList = otherartistsList;
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
    public String getAlbum()
    {
        return album;
    }
    public void setAlbum(String album)
    {
        this.album = album;
    }
    public String getArtistName()
    {
        return artistName;
    }
    public void setArtistName(String artistName)
    {
        this.artistName = artistName;
    }
    public List<String> getOtherArtistList()
    {
        return otherartistsList;
    }
    public void setOtherArtistList(List<String> otherArtistList)
    {
        this.otherartistsList = otherArtistList;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }




}
