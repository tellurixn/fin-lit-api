package ru.tellurian.fin_lit_api.exception.user;

import ru.tellurian.fin_lit_api.exception.system.ResponseCode;
import ru.tellurian.fin_lit_api.exception.system.SystemException;

public class UserNotFoundException extends SystemException {

    public UserNotFoundException() {
        description = new ResponseCode(ResponseCode.Code.User.USER_NOT_FOUND, "User not found");
    }
}
