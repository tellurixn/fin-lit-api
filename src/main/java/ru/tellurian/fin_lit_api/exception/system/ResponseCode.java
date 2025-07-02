package ru.tellurian.fin_lit_api.exception.system;

/**
 * Список кодов ошибок
 * created 01.07.2025
 * */
public record ResponseCode(int code, String message) {

    public static final class Code {
        /**
         * Ошибки при работе с пользователем
         */
        public static final class User {

            /**
             * Пользователь не найден
             */
            public static final int USER_NOT_FOUND = 401;
        }
    }

}
