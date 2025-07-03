package ru.tellurian.fin_lit_api.repository.user_budget;

import org.springframework.data.repository.Repository;
import ru.tellurian.fin_lit_api.model.entity.user.User;
import ru.tellurian.fin_lit_api.model.entity.user_budget.UserMonthlyBudget;

import java.util.Optional;

public interface UserBudgetRepository extends Repository<UserMonthlyBudget, Integer> {
    Optional<UserMonthlyBudget> findById(Integer id);

    Optional<UserMonthlyBudget> findByUser(User user);

    UserMonthlyBudget save(UserMonthlyBudget userMonthlyBudget);
}
