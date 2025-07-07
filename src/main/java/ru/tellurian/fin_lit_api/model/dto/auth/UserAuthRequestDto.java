package ru.tellurian.fin_lit_api.model.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Информация о пользователе")
public class UserAuthRequestDto {

    @Schema(description = "Логин", requiredMode = Schema.RequiredMode.REQUIRED, example = "testik")
    private String login;

    @Schema(description = "Пароль", requiredMode = Schema.RequiredMode.REQUIRED, example = "123")
    private String password;

}
