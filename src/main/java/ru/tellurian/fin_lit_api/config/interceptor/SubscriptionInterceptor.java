package ru.tellurian.fin_lit_api.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import ru.tellurian.fin_lit_api.constant.RequestAttributes;
import ru.tellurian.fin_lit_api.exception.subscription.SubscriptionNotFoundException;
import ru.tellurian.fin_lit_api.model.entity.subscription.Subscription;
import ru.tellurian.fin_lit_api.repository.subscription.SubscriptionRepository;

import java.util.Map;
import java.util.TreeMap;

@Log4j2
@Order(2)
@Component
public class SubscriptionInterceptor extends AbstractInterceptor {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception, SubscriptionNotFoundException {
        Map pathVariables = new TreeMap<>((Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
        int subscriptionId = Integer.parseInt(pathVariables.get(RequestAttributes.SUBSCRIPTION_ID).toString());
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(SubscriptionNotFoundException::new);
        request.setAttribute(RequestAttributes.SUBSCRIPTION, subscription);
        return true;
    }
}