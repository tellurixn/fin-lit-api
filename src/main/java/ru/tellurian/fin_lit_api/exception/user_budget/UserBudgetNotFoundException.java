package ru.tellurian.fin_lit_api.exception.user_budget;

import ru.tellurian.fin_lit_api.exception.system.ResponseCode;
import ru.tellurian.fin_lit_api.exception.system.SystemException;

public class UserBudgetNotFoundException extends SystemException {

    public UserBudgetNotFoundException() {
        description = new ResponseCode(ResponseCode.Code.User.Budget.BUDGET_NOT_FOUND, "User budget not found");
    }
}
