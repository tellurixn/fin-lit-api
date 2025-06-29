package ru.tellurian.fin_lit_api.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.tellurian.fin_lit_api.constant.RequestAttributes;

import java.util.UUID;

/**
 * Общая предобработка запросов
 * created 17.06.2025
 * */
@Log4j2
@Order(0)
@Component
public class RequestInterceptor extends AbstractInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(RequestAttributes.REQUEST_ID, UUID.randomUUID().toString());
        return true;
    }
}
