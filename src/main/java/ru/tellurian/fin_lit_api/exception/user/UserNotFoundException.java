package ru.tellurian.fin_lit_api.exception.user;

import ru.tellurian.fin_lit_api.exception.ResponseCode;
import ru.tellurian.fin_lit_api.exception.system.SystemException;

public class UserNotFoundException extends SystemException {

    @Override
    public String getDescription() {
        return String.format(
                getPattern(),
                ResponseCode.Code.User.USER_NOT_FOUND,
                ResponseCode.Code.User.USER_NOT_FOUND
        );
    }
}
