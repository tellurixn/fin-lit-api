package ru.tellurian.fin_lit_api.exception.system;

import lombok.Getter;

@Getter
public abstract class SystemException extends Exception {

    protected abstract String getDescription();

    protected final String pattern = "Error code %d, description: %s";
}
