package ru.tellurian.fin_lit_api.endpoint.subscription;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tellurian.fin_lit_api.constant.EndPointMapping;
import ru.tellurian.fin_lit_api.constant.RequestAttributes;
import ru.tellurian.fin_lit_api.exception.subscription.SubscriptionNotFoundException;
import ru.tellurian.fin_lit_api.model.dto.subscription.request.CreateSubscriptionRequestDto;
import ru.tellurian.fin_lit_api.model.dto.subscription.request.UpdateSubscriptionRequestDto;
import ru.tellurian.fin_lit_api.model.dto.subscription.response.GetSubscriptionResponseDto;
import ru.tellurian.fin_lit_api.model.dto.system.ResponseWrapper;
import ru.tellurian.fin_lit_api.model.entity.subscription.Subscription;
import ru.tellurian.fin_lit_api.model.entity.user.User;
import ru.tellurian.fin_lit_api.service.subscription.SubscriptionService;

import java.util.List;

/**
 * Действия с подписками пользователей
 * */
@RestController
@Tag(name = "Subscription", description = "Действия с подписками пользователя")
@SecurityRequirement(name = "token")
public class SubscriptionEndPoint {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping(EndPointMapping.Api.V1.User.Subscription.CHOOSE_SUBSCRIPTION)
    @Operation(summary = "Получить информацию о подписке пользователя")
    public ResponseWrapper<GetSubscriptionResponseDto> getUserSubscription(
            HttpServletRequest context,

            @PathVariable int userId,

            @PathVariable int subscriptionId
    ) throws SubscriptionNotFoundException {
        String requestId = (String) context.getAttribute(RequestAttributes.REQUEST_ID);
        User user = (User) context.getAttribute(RequestAttributes.USER);
        Subscription subscription = (Subscription) context.getAttribute(RequestAttributes.SUBSCRIPTION);
        GetSubscriptionResponseDto subscriptionDto = new GetSubscriptionResponseDto(subscription);
        return new ResponseWrapper<>(subscriptionDto, requestId);
    }

    @GetMapping(EndPointMapping.Api.V1.User.Subscription.SUBSCRIPTIONS)
    @Operation(summary = "Получить информацию о подписках пользователя")
    public ResponseWrapper<List<GetSubscriptionResponseDto>> getUserSubscriptions(
            HttpServletRequest context,

            @PathVariable int userId
    ) {
        String requestId = (String) context.getAttribute(RequestAttributes.REQUEST_ID);
        User user = (User) context.getAttribute(RequestAttributes.USER);
        List<Subscription> subscriptions = subscriptionService.getAllByUser(user);
        List<GetSubscriptionResponseDto> subscriptionsDto = subscriptions.stream().map(GetSubscriptionResponseDto::new).toList();
        return new ResponseWrapper<>(subscriptionsDto, requestId);
    }

    @PostMapping(EndPointMapping.Api.V1.User.Subscription.SUBSCRIPTION)
    @Operation(summary = "Добавить подписку пользователю")
    public ResponseWrapper<GetSubscriptionResponseDto> addSubscription(
            HttpServletRequest context,

            @PathVariable int userId,

            @RequestBody CreateSubscriptionRequestDto request
    ) {
        String requestId = (String) context.getAttribute(RequestAttributes.REQUEST_ID);
        User user = (User) context.getAttribute(RequestAttributes.USER);
        GetSubscriptionResponseDto subscriptionDto = new GetSubscriptionResponseDto(subscriptionService.create(user, request));
        return new ResponseWrapper<>(subscriptionDto, requestId);
    }

    @PutMapping(EndPointMapping.Api.V1.User.Subscription.CHOOSE_SUBSCRIPTION)
    @Operation(summary = "Обновить подписку пользователя")
    public ResponseWrapper<GetSubscriptionResponseDto> updateSubscription(
            HttpServletRequest context,

            @PathVariable int userId,

            @PathVariable int subscriptionId,

            @RequestBody UpdateSubscriptionRequestDto request
    ) throws SubscriptionNotFoundException {
        String requestId = (String) context.getAttribute(RequestAttributes.REQUEST_ID);
        User user = (User) context.getAttribute(RequestAttributes.USER);
        Subscription subscription = (Subscription) context.getAttribute(RequestAttributes.SUBSCRIPTION);
        Subscription updatedSubscription = subscriptionService.update(subscription, request);
        GetSubscriptionResponseDto updatedSubscriptionDto = new GetSubscriptionResponseDto(updatedSubscription);
        return new ResponseWrapper<>(updatedSubscriptionDto, requestId);
    }
}
