package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.model.AuthResult;
import pe.ctarequipa.tareo.application.model.Usuario;
import pe.ctarequipa.tareo.application.port.in.AuthUseCase;
import pe.ctarequipa.tareo.application.port.in.command.LoginCommand;
import pe.ctarequipa.tareo.application.port.out.AuthProvider;
import pe.ctarequipa.tareo.application.port.out.TokenGenerator;

public class AuthService implements AuthUseCase {

    private final AuthProvider authProvider;
    private final TokenGenerator tokenGenerator;

    public AuthService(AuthProvider authProvider, TokenGenerator tokenGenerator) {
        this.authProvider = authProvider;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public AuthResult login(LoginCommand command) {
        Usuario usuario = authProvider.autenticar(command.email(), command.password())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales invalidas"));
        String token = tokenGenerator.generar(usuario);
        return new AuthResult(
                token,
                usuario.id(),
                usuario.nombre(),
                usuario.email(),
                usuario.rolId()
        );
    }

    @Override
    public void logout() {
        // Stateless JWT: logout is client-side only
    }
}
