package ru.tellurian.fin_lit_api.repository;

import org.springframework.data.repository.Repository;
import ru.tellurian.fin_lit_api.model.entity.User;

import java.util.Optional;

public interface UserRepository extends Repository<User, Integer> {
    Optional<User> findById(int id);
}
