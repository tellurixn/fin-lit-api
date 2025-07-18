package ru.tellurian.fin_lit_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.tellurian.fin_lit_api.config.interceptor.RequestInterceptor;
import ru.tellurian.fin_lit_api.config.interceptor.SubscriptionInterceptor;
import ru.tellurian.fin_lit_api.config.interceptor.UserInterceptor;

/**
 * Конфигурация приложения
 * */
@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    @Autowired
    private RequestInterceptor requestInterceptor;
    @Autowired
    private UserInterceptor userInterceptor;
    @Autowired
    private SubscriptionInterceptor subscriptionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor)
                .addPathPatterns("/**");

        registry.addInterceptor(subscriptionInterceptor)
                .addPathPatterns("/**")
                .pathMatcher(new AntPathMatcher() {
                    @Override
                    public boolean match(String pattern, String path) {
                        return path.matches(".*/subscription/\\d+.*");
                    }
                });


        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**")
                .pathMatcher(new AntPathMatcher() {
                    @Override
                    public boolean match(String pattern, String path) {
                        return path.matches(".*/user/\\d+/.*");
                    }
                });
    }
}
