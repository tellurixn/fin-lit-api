package ru.tellurian.fin_lit_api.exception.system;

import lombok.Getter;

@Getter
public abstract class SystemException extends Exception {

    public SystemException() {
    }

    protected ResponseCode description;
}
