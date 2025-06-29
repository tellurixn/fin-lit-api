package ru.tellurian.fin_lit_api.service.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tellurian.fin_lit_api.exception.user.UserNotFoundException;
import ru.tellurian.fin_lit_api.model.dto.user.budget.UserMonthlyBudgetDto;
import ru.tellurian.fin_lit_api.model.dto.user.budget.UserMonthlyBudgetUpdateDto;
import ru.tellurian.fin_lit_api.model.entity.User;
import ru.tellurian.fin_lit_api.model.entity.UserMonthlyBudget;
import ru.tellurian.fin_lit_api.repository.user.UserBudgetRepository;
import ru.tellurian.fin_lit_api.repository.user.UserRepository;

import java.util.HashSet;

@Service
@Log4j2
public class UserService implements UserDetailsService {

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


    public User getByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User u = getByLogin(login);
        return new org.springframework.security.core.userdetails.User(
                u.getLogin(),
                u.getPassword(),
                true,
                true,
                true,
                true,
                new HashSet<>());
    }
}
