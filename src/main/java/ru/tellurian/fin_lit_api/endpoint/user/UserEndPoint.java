package ru.tellurian.fin_lit_api.endpoint.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tellurian.fin_lit_api.constant.EndPointMapping;
import ru.tellurian.fin_lit_api.constant.RequestAttributes;
import ru.tellurian.fin_lit_api.model.dto.ResponseWrapper;
import ru.tellurian.fin_lit_api.model.dto.user.auth.UserAuthRequestDto;
import ru.tellurian.fin_lit_api.model.entity.User;
import ru.tellurian.fin_lit_api.repository.security.JwtTokenRepository;
import ru.tellurian.fin_lit_api.repository.user.UserRepository;
import ru.tellurian.fin_lit_api.service.user.UserService;

@RestController
@Tag(name = "User", description = "Действия с пользователями")
public class UserEndPoint {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenRepository tokenRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping(EndPointMapping.Api.V1.User.LOGIN)
    @Operation(summary = "Аутентификация пользователя")
    public ResponseWrapper<?> login(
            HttpServletRequest context,

            @RequestBody @Valid UserAuthRequestDto credentials
    ) {
        String requestId = (String) context.getAttribute(RequestAttributes.REQUEST_ID);
        Authentication auth = null;

        try {
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    credentials.getLogin(),
                    credentials.getPassword())
            );
        } catch (BadCredentialsException e) {
            return new ResponseWrapper<>(HttpStatus.UNAUTHORIZED, requestId);
        }

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = tokenRepository.generateToken(auth);
        return new ResponseWrapper<>(jwt, requestId);
    }


    @PostMapping(EndPointMapping.Api.V1.User.REGISTER)
    @Operation(summary = "Регистрация пользователя")
    public ResponseWrapper<?> register(
            HttpServletRequest context,

            @RequestBody @Valid UserAuthRequestDto credentials
    ) {
        String requestId = (String) context.getAttribute(RequestAttributes.REQUEST_ID);
        User user = new User();
        user.setLogin(credentials.getLogin());
        user.setPassword(passwordEncoder.encode(credentials.getPassword()));
        userRepository.save(user);

        return new ResponseWrapper<>();
    }
}
