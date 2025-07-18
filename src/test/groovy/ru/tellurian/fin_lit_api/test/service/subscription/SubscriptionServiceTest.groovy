package ru.tellurian.fin_lit_api.test.service.subscription

import org.springframework.beans.factory.annotation.Autowired
import ru.tellurian.fin_lit_api.model.dto.subscription.request.CreateSubscriptionRequestDto
import ru.tellurian.fin_lit_api.model.dto.subscription.request.UpdateSubscriptionRequestDto
import ru.tellurian.fin_lit_api.model.entity.subscription.Subscription
import ru.tellurian.fin_lit_api.model.entity.user.User
import ru.tellurian.fin_lit_api.service.subscription.SubscriptionService
import ru.tellurian.fin_lit_api.test.FinLitApiTest
import spock.lang.Unroll

class SubscriptionServiceTest extends FinLitApiTest {

    public static final String NAME = "test_unit"
    public static final String NEW_NAME = "test_unit_new_name"
    public static final Long COST = 13333L
    public static final Long NEW_COST = 155L

    @Autowired
    private SubscriptionService subscriptionService

    @Unroll
    def "should create user's subscription"() {
        setup:
        User user = createUser()
        when:
        CreateSubscriptionRequestDto request = new CreateSubscriptionRequestDto(NAME, COST)
        Subscription subscription = subscriptionService.create(user, request)
        println subscription
        then:
        NAME == subscription.getName()
        COST == subscription.getCost()
        user.getId() == subscription.getUser().getId()
    }

    @Unroll
    def "should get user's subscription"() {
        setup:
        User user = createUser()
        Subscription subscription = createSubscription(user)
        int createdSubscriptionId = subscription.getId()
        when:
        List<Subscription> found = subscriptionService.getAllByUser(user)
        int foundCreatedSubscriptionId = found.get(0).getId()
        println found
        then:
        found != null
        found.size() == 1
        foundCreatedSubscriptionId == createdSubscriptionId
    }

    @Unroll
    def "should update user's subscription"() {
        setup:
        User user = createUser()
        Subscription subscription = createSubscription(user)
        UpdateSubscriptionRequestDto request = new UpdateSubscriptionRequestDto(NEW_NAME, NEW_COST)
        when:
        Subscription updated = subscriptionService.update(subscription, request)
        println updated
        then:
        updated != null
        updated.getName() == NEW_NAME
        updated.getCost() == NEW_COST
    }
}
