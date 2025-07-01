package ru.tellurian.fin_lit_api.exception;

/**
 * Список кодов ошибок
 * created 01.07.2025
 * */
public record ResponseCode() {

    public static final class Message {

        public static final class User {

            public static final String USER_NOT_FOUND = "User not found";
        }
    }


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
