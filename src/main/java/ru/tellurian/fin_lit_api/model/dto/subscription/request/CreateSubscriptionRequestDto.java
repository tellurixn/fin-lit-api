package ru.tellurian.fin_lit_api.model.dto.subscription.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO запроса на добавление подписки пользователю
 * created 07.07.2025
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@Schema(description = "Запрос добавления подписки пользователю")
public class CreateSubscriptionRequestDto {

    @NotNull
    @Size(min = 1, max = 255)
    @Schema(description = "Наименование новой подписки", requiredMode = Schema.RequiredMode.REQUIRED, example = "Яндекс.Плюс")
    private String name;

    @NotNull
    @Schema(description = "Стоимость новой подписки, в копейках", requiredMode = Schema.RequiredMode.REQUIRED, example = "150000")
    private Long cost;
}
