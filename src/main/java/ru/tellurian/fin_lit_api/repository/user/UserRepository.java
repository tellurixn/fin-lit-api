package ru.tellurian.fin_lit_api.repository.user;

import org.springframework.data.repository.Repository;
import ru.tellurian.fin_lit_api.model.entity.user.User;

import java.util.Optional;

public interface UserRepository extends Repository<User, Integer> {

    Optional<User> findById(int id);

    Optional<User> findByLogin(String login);

    User save(User user);
}
