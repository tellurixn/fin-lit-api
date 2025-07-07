package ru.tellurian.fin_lit_api.service.subscription;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tellurian.fin_lit_api.exception.subscription.SubscriptionNotFoundException;
import ru.tellurian.fin_lit_api.model.dto.subscription.request.CreateSubscriptionRequestDto;
import ru.tellurian.fin_lit_api.model.entity.subscription.Subscription;
import ru.tellurian.fin_lit_api.model.entity.user.User;
import ru.tellurian.fin_lit_api.repository.subscription.SubscriptionRepository;

import java.util.List;

@Log4j2
@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription getById(Integer id) throws SubscriptionNotFoundException {
        return subscriptionRepository.findById(id).orElseThrow(SubscriptionNotFoundException::new);
    }

    public List<Subscription> getAllByUser(User user) {
        return subscriptionRepository.findByUser(user);
    }

    public Subscription create(User user, CreateSubscriptionRequestDto createRequest) {
        Subscription subscription = new Subscription();
        subscription.setName(createRequest.getName());
        subscription.setCost(createRequest.getCost());
        subscription.setUser(user);
        return subscriptionRepository.save(subscription);
    }
}
