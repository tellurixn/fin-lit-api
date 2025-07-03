package ru.tellurian.fin_lit_api.service.user_budget;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tellurian.fin_lit_api.exception.user_budget.UserBudgetNotFoundException;
import ru.tellurian.fin_lit_api.model.entity.user.User;
import ru.tellurian.fin_lit_api.model.entity.user_budget.UserMonthlyBudget;
import ru.tellurian.fin_lit_api.repository.user_budget.UserBudgetRepository;

@Service
@Log4j2
public class UserBudgetService {

    @Autowired
    private UserBudgetRepository userBudgetRepository;

    public UserMonthlyBudget getUserMonthlyBudgetByUser(User user) throws UserBudgetNotFoundException {
        return  userBudgetRepository.findByUser(user).orElseThrow(UserBudgetNotFoundException::new);
    }

}
