package ru.tellurian.fin_lit_api.model.dto.subscription.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.tellurian.fin_lit_api.model.entity.subscription.Subscription;

/**
 * DTO подписки пользователя
 * created 07.07.2025
 */

@Getter
@Setter
@ToString
@Schema(description = "Подписка пользователя")
public class GetSubscriptionResponseDto {

    @Schema(description = "Идентификатор подписки пользователя", requiredMode = Schema.RequiredMode.REQUIRED, example = "1234567")
    private int id;

    @Schema(description = "Наименование подписки пользователя", requiredMode = Schema.RequiredMode.REQUIRED, example = "Яндекс.Плюс")
    private String name;

    @Schema(description = "Месячная стоимость подписки, в копейках", requiredMode = Schema.RequiredMode.REQUIRED, example = "15000")
    private Long cost;

    public GetSubscriptionResponseDto(Subscription subscription) {
        this.id = subscription.getId();
        this.name = subscription.getName();
        this.cost = subscription.getCost();
    }
}
