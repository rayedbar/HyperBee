package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.User;

import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:44 AM
 */

public interface UserService {

    public void createUser(User user);

    public User findById(int id);

    public User findByUsername(String username);

    public List<User> findAll();

    public void updateUser(User user);

    public void deleteUser(int id);
}