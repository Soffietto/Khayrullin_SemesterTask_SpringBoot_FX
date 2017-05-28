package ru.kpfu.itis.khayrullin.service;

import ru.kpfu.itis.khayrullin.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    void delete(User user);

    User findByLogin(String login);

    List<User> getAll();
}
