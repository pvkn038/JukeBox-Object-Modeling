package com.crio.jukebox.services;
import java.util.Optional;
import com.crio.jukebox.entities.User;
public interface IUserService {
    public User CreateUser(String name);
    public Optional<User> getUser(String id);
}
