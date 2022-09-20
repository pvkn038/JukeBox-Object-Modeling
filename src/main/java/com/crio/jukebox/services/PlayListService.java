package com.crio.jukebox.services;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.dtos.modifiedPlayList;
import com.crio.jukebox.dtos.playingSong;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.exceptions.*;
import com.crio.jukebox.entities.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PlayListService implements IPlayListService {
    IPlayListRepository playListRepository;
    ISongRepository songRepository;
    IUserRepository userRepository;
    public PlayListService(IPlayListRepository playListRepository,ISongRepository songRepository,IUserRepository userRepository)
    {
        this.playListRepository = playListRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }
    public String create(String userid,String name,List<String> songId)throws UserNotFoundException,SongNotFoundException
    {
        User owner = userRepository.findById(userid).orElseThrow(() -> new UserNotFoundException("Cannot create playList. User for given id:" + userid + " not found!"));
        List<Song> songList = new ArrayList<>();

        for(String id : songId){
            Song song = songRepository.findById(id).orElseThrow(()-> new SongNotFoundException("Cannot find song. Song id- "+songId+" is not present!"));
            songList.add(song);
        }

        PlayList playlist = new PlayList(name,owner,songList,songId.get(0));
        PlayList resultantPlaylist = playListRepository.save(playlist);

        List<PlayList> oldPlaylist = owner.getplayList();
        if(oldPlaylist==null){
            List<PlayList> newPlaylist = new ArrayList<>();
            newPlaylist.add(resultantPlaylist);
            owner.setPlayList(newPlaylist);
        }else{
            oldPlaylist.add(resultantPlaylist);
            owner.setPlayList(oldPlaylist);
        }    

        return resultantPlaylist.getId();
        
    }
    public String delete(String userid,String id)
    {
        PlayList playlist = playListRepository.findById(id).orElseThrow(()-> new PlayListNotFoundException("Cannot find playlist, playlist id- "+id+" is not present!"));
        User owner = userRepository.findById(userid).orElseThrow(() -> new UserNotFoundException("Cannot create playList. User for given id:" + userid + " not found!"));

        List<PlayList> oldPlaylist = owner.getplayList();
        oldPlaylist.remove(playlist);
        owner.setPlayList(oldPlaylist);
        
        playListRepository.deleteById(id);

        return "Delete Successful";
    }
    public modifiedPlayList addSong(String userId,String id,List<String> songId)
    {
        PlayList playlist = playListRepository.findById(id).orElseThrow(()-> new PlayListNotFoundException("Cannot find playlist, playlist id- "+id+" is not present!"));
        User owner = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Cannot create playList. User for given id:" + userId + " not found!"));

        List<Song> songList = playlist.getsongsList();

        for(String sId : songId){
            Song songFound = songRepository.findById(sId).orElseThrow(()-> new SongNotFoundException("Cannot find song. Song id- "+songId+" is not present!"));
            songList.add(songFound);
        }

        songList = songList.stream().distinct().collect(Collectors.toList());

        playlist.setsongsList(songList);

        playListRepository.deleteById(id);
        playlist = playListRepository.save(playlist);

        List<PlayList> newPlaylist = new ArrayList<>();
        List<PlayList> listOfPlaylist = owner.getplayList();
        
        for(PlayList playlist2 : listOfPlaylist){
            if(playlist2.getId().equals(id))
                continue;
            newPlaylist.add(playlist2);
        }

        newPlaylist.add(playlist);
        owner.setPlayList(newPlaylist);
        userRepository.deleteById(userId);
        userRepository.save(owner);

        List<Song> finalSongList = playlist.getsongsList();
        List<String> finalSongId = new ArrayList<>();

        for(Song sg : finalSongList){
            finalSongId.add(sg.getId());
        }

        return new modifiedPlayList(id,playlist.getName(),finalSongId);
    }
    public modifiedPlayList deleteSong(String userId,String id,List<String> songId)
    {
        PlayList playlist = playListRepository.findById(id).orElseThrow(()-> new PlayListNotFoundException("Cannot find playlist, playlist id- "+id+" is not present!"));
        User owner = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Cannot create playList. User for given id:" + userId + " not found!"));

        List<Song> songList = playlist.getsongsList();

        for(String sId : songId){
            for(Song song : songList){
                if(song.getId().equals(sId)){
                    break;
                }
            }
            Song songFound = songRepository.findById(sId).orElseThrow(()-> new SongNotFoundException("Cannot find song. Song id- "+songId+" is not present!"));
            songList.remove(songFound);
        }

        playListRepository.deleteById(id);
        playlist = playListRepository.save(playlist);

        List<PlayList> newPlaylist = new ArrayList<>();
        List<PlayList> listOfPlaylist = owner.getplayList();
        
        for(PlayList playlist2 : listOfPlaylist){
            if(playlist2.getId().equals(id))
                continue;
            newPlaylist.add(playlist2);
        }

        newPlaylist.add(playlist);
        owner.setPlayList(newPlaylist);
        userRepository.deleteById(userId);
        userRepository.save(owner);

        List<Song> finalSongList = playlist.getsongsList();
        List<String> finalSongId = new ArrayList<>();

        for(Song sg : finalSongList){
            finalSongId.add(sg.getId());
        }

        return new modifiedPlayList(id,playlist.getName(),finalSongId);
    }
    public playingSong PlayPlaylist(String userId,String id)
    {
        PlayList playlist = playListRepository.findById(id).orElseThrow(()-> new PlayListNotFoundException("Cannot find playlist, playlist id- "+id+" is not present!"));
        User owner = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Cannot create playList. User for given id:" + userId + " not found!"));
        

        String currentSongId = playlist.getCurrentSongId();

        Song song = songRepository.findById(currentSongId).orElseThrow(()-> new SongNotFoundException("Cannot find song. Song id- "+currentSongId+" is not present!"));
        
        owner.setcurrentPlayList(playlist);

        userRepository.deleteById(userId);
        User newUser = userRepository.save(owner);

        return new playingSong(song.getName(), song.getAlbum(), song.getOtherArtistList());
    }
    public playingSong PlayNextSong(String userId,String button)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Cannot create playList. User for given id:" + userId + " not found!"));
        PlayList activePlaylist = user.getcurrentPlayList();
        String activeSongId = activePlaylist.getCurrentSongId();
    
        
        int n = activePlaylist.getsongsList().size();
        int i=0;
        int currentIndex = -1;
        for(Song sn : activePlaylist.getsongsList()){
            if(sn.getId().equals(activeSongId)){
                currentIndex = i;
                break;
            }
            i++;
        }

        int nextIndex = (currentIndex+1)%n;
        Song requiredSong = activePlaylist.getsongsList().get(nextIndex);

        //Changing the currentSongId in Playlist.java
        activePlaylist.setCurrentSongId(requiredSong.getId());
        playListRepository.deleteById(activePlaylist.getId());
        PlayList newPlaylist = playListRepository.save(activePlaylist);

        List<PlayList> listOfPlaylists = user.getplayList();
        int it = 0;
        for(PlayList playlist : listOfPlaylists){
            if(playlist.getId().equals(activePlaylist.getId())){
                listOfPlaylists.remove(it);
                break;
            }
            it++;
        }
        listOfPlaylists.add(activePlaylist);
        user.setPlayList(listOfPlaylists);
        userRepository.deleteById(userId);
        User newUser = userRepository.save(user);
        return new playingSong(requiredSong.getName(), requiredSong.getAlbum(), requiredSong.getOtherArtistList());
    }
    public playingSong PlayPreviousSong(String userId,String button)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Cannot create playList. User for given id:" + userId + " not found!"));
        PlayList activePlaylist = user.getcurrentPlayList();
        String activeSongId = activePlaylist.getCurrentSongId();
    
        
        int n = activePlaylist.getsongsList().size();
        int i=0;
        int currentIndex = -1;
        for(Song sn : activePlaylist.getsongsList()){
            if(sn.getId().equals(activeSongId)){
                currentIndex = i;
                break;
            }
            i++;
        }

        int nextIndex;

        if(currentIndex==0)
            nextIndex = n-1;
        else
            nextIndex = (currentIndex - 1)%n;
    

        Song requiredSong = activePlaylist.getsongsList().get(nextIndex);

        //Changing the currentSongId in Playlist.java
        activePlaylist.setCurrentSongId(requiredSong.getId());
        playListRepository.deleteById(activePlaylist.getId());
        PlayList newPlaylist = playListRepository.save(activePlaylist);

        List<PlayList> listOfPlaylists = user.getplayList();
        int it = 0;
        for(PlayList playlist : listOfPlaylists){
            if(playlist.getId().equals(activePlaylist.getId())){
                listOfPlaylists.remove(it);
                break;
            }
            it++;
        }
        listOfPlaylists.add(activePlaylist);
        user.setPlayList(listOfPlaylists);
        userRepository.deleteById(userId);
        User newUser = userRepository.save(user);
        return new playingSong(requiredSong.getName(), requiredSong.getAlbum(), requiredSong.getOtherArtistList());
    }
    public playingSong PlaySpecificSong(String userId,String songId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Cannot create playList. User for given id:" + userId + " not found!"));
       
        PlayList activePlaylist = user.getcurrentPlayList();
        List<Song> listOfSongs = activePlaylist.getsongsList();

        Song song = null;
        for(Song sn : listOfSongs){
            if(sn.getId().equals(songId)){
                song = sn;
                break;
            }
        }
        
        if(song==null)
            throw new SongNotFoundException("Cannot find song. Song id- "+songId+" is not present!");

       
        activePlaylist.setCurrentSongId(songId);
        playListRepository.deleteById(activePlaylist.getId());
        PlayList newPlaylist = playListRepository.save(activePlaylist);

        List<PlayList> listOfPlaylists = user.getplayList();
        int i = 0;
        for(PlayList playlist : listOfPlaylists){
            if(playlist.getId().equals(activePlaylist.getId())){
                listOfPlaylists.remove(i);
                break;
            }
            i++;
        }
        listOfPlaylists.add(activePlaylist);
        user.setPlayList(listOfPlaylists);
        userRepository.deleteById(userId);
        User newUser = userRepository.save(user);
        return new playingSong(song.getName(), song.getAlbum(), song.getOtherArtistList());
    }
}
