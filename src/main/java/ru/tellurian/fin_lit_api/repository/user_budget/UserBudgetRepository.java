package ru.tellurian.fin_lit_api.repository.user_budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tellurian.fin_lit_api.model.entity.user.User;
import ru.tellurian.fin_lit_api.model.entity.user_budget.UserMonthlyBudget;

import java.util.Optional;

@Repository
public interface UserBudgetRepository extends JpaRepository<UserMonthlyBudget, Integer> {
    Optional<UserMonthlyBudget> findById(Integer id);

    Optional<UserMonthlyBudget> findByUser(User user);

    UserMonthlyBudget save(UserMonthlyBudget userMonthlyBudget);
}
