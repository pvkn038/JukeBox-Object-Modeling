package com.crio.jukebox.dtos;
import java.util.List;
public class playingSong {
    private String songName;
    private String album;
    private List<String> artists;

    public playingSong(){}
    public playingSong(String songName,String album,List<String> artists)
    {
        this.songName = songName;
        this.album = album;
        this.artists = artists;
    }

    public String getsongName()
    {
        return songName;
    }
    public void setsongName(String songName)
    {
        this.songName = songName;
    }
    public String getalbum()
    {
        return album;
    }
    public void setalbum(String album)
    {
        this.album = album;
    }
    public List<String> getArtists()
    {
        return artists;
    }
    public void setArtists(List<String> artists)
    {
        this.artists = artists;
    }
}
