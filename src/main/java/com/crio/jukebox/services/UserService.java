package com.crio.jukebox.services;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IUserRepository;
import java.util.Optional;
public class UserService implements IUserService {
    IUserRepository IuserRepository;

    public UserService(IUserRepository IuserRepository)
    {
        this.IuserRepository = IuserRepository;
    }
    @Override
    public User CreateUser(String name)
    {
        User user = new User(name);
        return IuserRepository.save(user);
    }
    @Override
    public Optional<User> getUser(String id)
    {
        return IuserRepository.findById(id);
    }
}
