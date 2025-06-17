package ru.tellurian.fin_lit_api.endpoint.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tellurian.fin_lit_api.constant.EndPointMapping;
import ru.tellurian.fin_lit_api.constant.RequestAttributes;
import ru.tellurian.fin_lit_api.model.dto.ResponseWrapper;
import ru.tellurian.fin_lit_api.model.dto.user.budget.UserMonthlyBudgetDto;
import ru.tellurian.fin_lit_api.model.dto.user.budget.UserMonthlyBudgetUpdateDto;
import ru.tellurian.fin_lit_api.model.entity.User;
import ru.tellurian.fin_lit_api.service.UserService;

@RestController
@Tag(name = "UserBudget", description = "Действия с расходами пользователя")
public class UserBudgetEndPoint {

    @Autowired
    private UserService userService;

    @GetMapping(EndPointMapping.Api.V1.User.Budget.BUDGET)
    @Operation(summary = "Получить информацию о месячных расходах пользоватиеля")
    public ResponseWrapper<UserMonthlyBudgetDto> getUserBudget(
            HttpServletRequest context,

            @PathVariable int userId
    ) {
        String requestId = (String) context.getAttribute(RequestAttributes.REQUEST_ID);
        User user = (User) context.getAttribute(RequestAttributes.USER);
        return new ResponseWrapper<>(userService.getUserMonthlyBudget(user), requestId);
    }

    @PutMapping(EndPointMapping.Api.V1.User.Budget.BUDGET)
    @Operation(summary = "Обновление расходов пользователя")
    public ResponseWrapper<UserMonthlyBudgetDto> updateUserBudget(
            HttpServletRequest context,

            @PathVariable @Positive int userId,

            @RequestBody UserMonthlyBudgetUpdateDto request
    ) {
        String requestId = (String) context.getAttribute(RequestAttributes.REQUEST_ID);
        User user = (User) context.getAttribute("user");
        return new ResponseWrapper<>(userService.update(user, request), requestId);
    }
}
