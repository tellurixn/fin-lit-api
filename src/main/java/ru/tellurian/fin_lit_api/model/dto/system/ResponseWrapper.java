package ru.tellurian.fin_lit_api.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Обертка для ответов
 * created 17.06.2025
 * */
@Getter
@Setter
@ToString
@AllArgsConstructor
@Schema(description = "Ответ на запрос")
public class ResponseWrapper<T> {

    @Schema(description = "Идентификатор запроса",  requiredMode = Schema.RequiredMode.REQUIRED, example = "f169045e-8584-48a1-aea8-194e29f147e9")
    private String requestId;

    @Schema(description = "Полезная нагрузка", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private T response;

    @Schema(description = "Успех", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private boolean success;

    public ResponseWrapper(T response, String requestId) {
        this.response = response;
        this.requestId = requestId;
        this.success = true;
    }

    public ResponseWrapper() {
        this.response = null;
        this.requestId = requestId;
        this.success = true;
    }
}
