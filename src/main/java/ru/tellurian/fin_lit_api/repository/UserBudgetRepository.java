package ru.tellurian.fin_lit_api.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import ru.tellurian.fin_lit_api.model.entity.User;
import ru.tellurian.fin_lit_api.model.entity.UserMonthlyBudget;

import java.util.Optional;


public interface UserBudgetRepository extends Repository<UserMonthlyBudget, Integer> {
    Optional<UserMonthlyBudget> findById(Integer id);

    Optional<UserMonthlyBudget> findByUser(User user);

    UserMonthlyBudget save(UserMonthlyBudget userMonthlyBudget);
}
