package ru.tellurian.fin_lit_api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.tellurian.fin_lit_api.constant.RequestAttributes;
import ru.tellurian.fin_lit_api.exception.user.UserNotFoundException;
import ru.tellurian.fin_lit_api.model.dto.system.ResponseWrapper;

/**
 * Advice для обработки исключений
 * created 01.07.2025
 * */
@ControllerAdvice
public class GlobalControllerAdvice {

    /** Ошибка - пользователь не найден */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseWrapper<?> handleException(HttpServletRequest request, UserNotFoundException e) {
        String requestId = (String) request.getAttribute(RequestAttributes.REQUEST_ID);
        return new ResponseWrapper<>(e.getDescription(), requestId);
    };

    /** Ошибка - пользователь не найден */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseWrapper<?> handleException(HttpServletRequest request, UsernameNotFoundException e) {
        String requestId = (String) request.getAttribute(RequestAttributes.REQUEST_ID);
        return new ResponseWrapper<>(e.getMessage(), requestId);
    };
}
