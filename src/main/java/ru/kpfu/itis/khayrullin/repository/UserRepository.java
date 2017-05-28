package ru.kpfu.itis.khayrullin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.khayrullin.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByLogin(String login);
}
