package pe.ctarequipa.tareo.infrastructure.adapter.in.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.ctarequipa.tareo.application.model.AuthResult;
import pe.ctarequipa.tareo.application.port.in.AuthUseCase;
import pe.ctarequipa.tareo.application.port.in.command.LoginCommand;
import pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto.LoginRequest;
import pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto.LoginResponse;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        AuthResult result = authUseCase.login(new LoginCommand(request.email(), request.password()));
        return new LoginResponse(
                result.token(),
                String.valueOf(result.userId()),
                result.nombre(),
                result.email(),
                result.rol()
        );
    }

    @PostMapping("/logout")
    public void logout() {
        authUseCase.logout();
    }
}
