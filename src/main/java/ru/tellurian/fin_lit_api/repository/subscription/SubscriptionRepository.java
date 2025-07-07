package ru.tellurian.fin_lit_api.repository.subscription;

import org.springframework.data.repository.Repository;
import ru.tellurian.fin_lit_api.model.entity.subscription.Subscription;
import ru.tellurian.fin_lit_api.model.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends Repository<Subscription, Integer> {

    Optional<Subscription> findById(Integer id);

    List<Subscription> findByUser(User user);

    Subscription save(Subscription subscription);
}
