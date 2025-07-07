package ru.tellurian.fin_lit_api.exception.subscription;

import ru.tellurian.fin_lit_api.exception.system.ResponseCode;
import ru.tellurian.fin_lit_api.exception.system.SystemException;

public class SubscriptionNotFoundException extends SystemException {

    public SubscriptionNotFoundException() {
        description = new ResponseCode(
                ResponseCode.Code.User.Subscription.SUBSCRIPTION_NOT_FOUND,
                "Subscription not found"
        );
    }
}
