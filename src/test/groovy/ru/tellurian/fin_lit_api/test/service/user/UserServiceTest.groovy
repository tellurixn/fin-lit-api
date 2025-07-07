package ru.tellurian.fin_lit_api.test.service.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.tellurian.fin_lit_api.model.dto.user_budget.UserMonthlyBudgetDto
import ru.tellurian.fin_lit_api.model.dto.user_budget.request.UpdateUserMonthlyBudgetRequestDto
import ru.tellurian.fin_lit_api.model.entity.user.User
import ru.tellurian.fin_lit_api.model.entity.user_budget.UserMonthlyBudget
import ru.tellurian.fin_lit_api.service.user.UserService
import ru.tellurian.fin_lit_api.test.FinLitApiTest
import spock.lang.Unroll

@SpringBootTest
class UserServiceTest extends FinLitApiTest {

    @Autowired
    private UserService userService

    @Unroll
    def "should get monthly budget"() {
        setup:
        User user = createUser()
        UserMonthlyBudget budget = createMonthlyBudget(user.getId())
        println user
        println budget
        when:
        UserMonthlyBudgetDto found = userService.getUserMonthlyBudget(user)
        println found
        then:
        budget.getId() == found.getId()
    }

    @Unroll
    def "should update monthly budget"() {
        setup:
        User user = createUser()
        UserMonthlyBudget budget = createMonthlyBudget(user.getId())
        UpdateUserMonthlyBudgetRequestDto updated = new UpdateUserMonthlyBudgetRequestDto(5000000L, null, null, null, null)
        println user
        println budget
        when:
        UserMonthlyBudgetDto result = userService.update(user, updated)
        println result
        then:
        result.getMonthlyIncome() == updated.getMonthlyBudget()
        result.getApartmentRent() == updated.getApartmentRent()
        result.getUtility() == updated.getUtility()
        result.getInternet() == updated.getInternet()
        result.getHealth() == updated.getHealth()
    }

}
