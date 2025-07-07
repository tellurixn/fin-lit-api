package ru.tellurian.fin_lit_api.exception.system;

/**
 * Список кодов ошибок
 * created 01.07.2025
 * */
public record ResponseCode(int code, String message) {

    public static final class Code {
        /**
         * Ошибки при работе с пользователем (4xx-499)
         */
        public static final class User {

            /**
             * Пользователь не найден
             */
            public static final int USER_NOT_FOUND = 401;

            /**
             * Ошибки при работе с бюджетом пользователя (41x)
             */
            public static final class Budget {

                public static final int BUDGET_NOT_FOUND = 411;
            }

            /**
             * Ошибки при работе с подписками пользователя (42x)
             * */
            public static final class Subscription {

                public static final int SUBSCRIPTION_NOT_FOUND = 421;
            }
        }
    }

}
