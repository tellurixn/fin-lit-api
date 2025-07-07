package ru.tellurian.fin_lit_api.model.dto.user_budget.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Schema(description = "Обновление бюджета")
public class UpdateUserMonthlyBudgetRequestDto {

    @Schema(description = "Ежемесячный доход, в копейках", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "500000")
    private Long monthlyBudget;

    @Schema(description = "Аренда квартиры, в копейках", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "500000")
    private Long apartmentRent;

    @Schema(description = "Коммунальные платежи, в копейках", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "500000")
    private Long utility;

    @Schema(description = "Оплата интернета, в копейках", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "500000")
    private Long internet;

    @Schema(description = "Траты на здоровье, в копейках", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "500000")
    private Long health;
}
