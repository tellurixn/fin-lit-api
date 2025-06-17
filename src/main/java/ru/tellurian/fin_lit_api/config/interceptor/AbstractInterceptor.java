package ru.tellurian.fin_lit_api.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

public abstract class AbstractInterceptor implements HandlerInterceptor {

    @Override
    public abstract boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
