package ru.tellurian.fin_lit_api.config.filter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import ru.tellurian.fin_lit_api.repository.security.JwtTokenRepository;
import ru.tellurian.fin_lit_api.service.user.UserService;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenRepository tokenRepository;
    @Autowired
    private UserService userService;

    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = null;
        String login = null;
        UserDetails userDetails = null;
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            String headerAuth = request.getHeader("Authorization");
            if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
                jwt = headerAuth.substring(7);
            }
            if (jwt != null) {
                try {
                    login = tokenRepository.getNameFromJwtToken(jwt);
                } catch (ExpiredJwtException e) {
                    logger.error("Token expired!", e);
                }
                if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    /* Аутентификация */
                    userDetails = userService.loadUserByUsername(login);

                    /* Прокидываем пользователя в конекст */
                    authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            null);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        } catch (Exception e) {
           logger.error("Authorization error", e);
        }

        /* Дергаем следующий фильтр, если он есть в цепочке */
        filterChain.doFilter(request, response);
    }
}
