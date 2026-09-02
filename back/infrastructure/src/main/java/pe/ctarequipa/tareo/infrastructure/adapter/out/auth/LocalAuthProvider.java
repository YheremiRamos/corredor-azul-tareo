package pe.ctarequipa.tareo.infrastructure.adapter.out.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pe.ctarequipa.tareo.application.model.Usuario;
import pe.ctarequipa.tareo.application.port.out.AuthProvider;
import pe.ctarequipa.tareo.application.port.out.UsuarioRepository;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class LocalAuthProvider implements AuthProvider {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Usuario> autenticar(String emailOrUsername, String password) {
        return usuarioRepository.findByEmailOrUsername(emailOrUsername)
                .filter(Usuario::activo)
                .filter(u -> passwordEncoder.matches(password, u.passwordHash()));
    }
}
