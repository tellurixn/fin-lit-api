package ru.tellurian.fin_lit_api.service.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tellurian.fin_lit_api.model.dto.user.budget.UserMonthlyBudgetDto;
import ru.tellurian.fin_lit_api.model.dto.user.budget.UserMonthlyBudgetUpdateDto;
import ru.tellurian.fin_lit_api.model.entity.User;
import ru.tellurian.fin_lit_api.model.entity.UserMonthlyBudget;
import ru.tellurian.fin_lit_api.repository.UserBudgetRepository;
import ru.tellurian.fin_lit_api.repository.UserRepository;

@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBudgetRepository userBudgetRepository;

    public UserMonthlyBudgetDto getUserMonthlyBudget(User user) {
        UserMonthlyBudget userMonthlyBudget = userBudgetRepository.findByUser(user).orElseThrow(RuntimeException::new);
        return new UserMonthlyBudgetDto(userMonthlyBudget);
    }

    public UserMonthlyBudgetDto update(User user, UserMonthlyBudgetUpdateDto updated) {
        UserMonthlyBudget current = userBudgetRepository.findByUser(user).orElseThrow(RuntimeException::new);
        if (current.getMonthlyIncome() != updated.getMonthlyBudget()) {
            current.setMonthlyIncome(updated.getMonthlyBudget());
        }
        if (!current.getHealth().equals(updated.getHealth())) {
            current.setHealth(updated.getHealth());
        }
        if (!current.getUtility().equals(updated.getUtility())) {
            current.setUtility(updated.getUtility());
        }
        if (!current.getInternet() .equals(updated.getInternet())) {
            current.setInternet(updated.getInternet());
        }
        if (!current.getApartmentRent().equals(updated.getApartmentRent())) {
            current.setApartmentRent(updated.getApartmentRent());
        }
        UserMonthlyBudget saved = userBudgetRepository.save(current);
        return new UserMonthlyBudgetDto(saved);
    }


}
