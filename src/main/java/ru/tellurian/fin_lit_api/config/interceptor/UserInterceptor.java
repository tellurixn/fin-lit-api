package ru.tellurian.fin_lit_api.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import ru.tellurian.fin_lit_api.constant.RequestAttributes;
import ru.tellurian.fin_lit_api.exception.user.UserNotFoundException;
import ru.tellurian.fin_lit_api.model.entity.User;
import ru.tellurian.fin_lit_api.repository.UserRepository;

import java.util.Map;
import java.util.TreeMap;

@Log4j2
@Order(1)
@Component
public class UserInterceptor extends AbstractInterceptor {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map pathVariables = new TreeMap<>((Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
        int userId = Integer.parseInt(pathVariables.get("userId").toString());
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        request.setAttribute(RequestAttributes.USER, user);
        return true;
    }
}
