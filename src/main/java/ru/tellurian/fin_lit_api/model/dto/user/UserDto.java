package ru.tellurian.fin_lit_api.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.tellurian.fin_lit_api.model.entity.User;

@Getter
@Setter
@ToString
@Schema(description = "Информация о пользователе")
public class UserDto {

    @Schema(description = "Идентификатор пользователя", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private int id;

    @Schema(description = "Логин пользователя", requiredMode = Schema.RequiredMode.REQUIRED, example = "admin")
    private String login;

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
    }
}
