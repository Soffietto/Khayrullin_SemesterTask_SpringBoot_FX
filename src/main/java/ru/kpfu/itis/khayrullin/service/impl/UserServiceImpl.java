package ru.kpfu.itis.khayrullin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.khayrullin.model.User;
import ru.kpfu.itis.khayrullin.repository.UserRepository;
import ru.kpfu.itis.khayrullin.service.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @PostConstruct
    private void setAdmin(){
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        userRepository.save(user);
    }
}
