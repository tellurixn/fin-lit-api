package ru.tellurian.fin_lit_api.constant;

public final class EndPointMapping {

    public static final class Api {

        public static final String API = "/api";

        public static final class V1 {

            public static final String V1 = API + "/v1";

            public static final String V1_PATTERN = V1 + "/**";

            public static final class User {

                public static final String USER = V1 + "/user";

                public static final String CHOOSE_USER = USER + "/{userId}";

                public static final String REGISTER = USER + "/register";

                public static final String LOGIN = USER + "/login";

                public static final String LOGOUT = USER + "/logout";

                public static final class Budget {

                    public static final String BUDGET = CHOOSE_USER + "/budget";

                    private Budget() {}
                }

                public static final class Subscription {

                    public static final String SUBSCRIPTION = CHOOSE_USER + "/subscription";

                    public static final String SUBSCRIPTIONS = SUBSCRIPTION + "s";

                    public static final String CHOOSE_SUBSCRIPTION = SUBSCRIPTION + "/{subscriptionId}";

                    private Subscription() {};
                }

                private User() {}
            }

            private V1() {}
        }

        private Api() {}
    }

    private EndPointMapping() {}
}
