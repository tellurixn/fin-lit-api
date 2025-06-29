package ru.tellurian.fin_lit_api.model.dto.user.budget;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.tellurian.fin_lit_api.model.entity.user_budget.UserMonthlyBudget;

/**
 * DTO ежемесячных расходов пользователя
 * created 15.06.2025
 */

@Getter
@Setter
@ToString
@Schema(description = "Ежемесячные расходы пользователя")
public class UserMonthlyBudgetDto {

    @Schema(description = "Идентификатор бюджета пользователя", requiredMode = Schema.RequiredMode.REQUIRED, example = "1234567")
    private int id;

    @Schema(description = "Ежемесячный доход, в копейках", requiredMode = Schema.RequiredMode.REQUIRED, example = "5000000")
    private long monthlyIncome;

    @Schema(description = "Аренда квартиры, в копейках", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "")
    private Long apartmentRent;

    @Schema(description = "Коммунальные платежи, в копейках", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "")
    private Long utility;

    @Schema(description = "Расходы на интернет, в копейках", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "")
    private Long internet;

    @Schema(description = "Траты на здоровье, в копейках", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "")
    private Long health;

    @Schema(description = "Идентификатор пользователя", requiredMode = Schema.RequiredMode.REQUIRED, example = "23412")
    private int userId;

    public UserMonthlyBudgetDto(UserMonthlyBudget userBudget) {
        this.id = userBudget.getId();
        this.monthlyIncome = userBudget.getMonthlyIncome();
        this.apartmentRent = userBudget.getApartmentRent();
        this.utility = userBudget.getUtility();
        this.internet = userBudget.getInternet();
        this.health = userBudget.getHealth();
        this.userId = userBudget.getUser().getId();
    }

}