package pe.ctarequipa.tareo.infrastructure.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository.UsuarioJpaRepository;

import java.util.List;

@Slf4j
@Component
@Profile("dev")
@RequiredArgsConstructor
public class DevPasswordInitializer implements ApplicationRunner {

    private static final String DEV_PASSWORD = "admin123";
    private static final List<String> SEED_USERNAMES = List.of("admin", "shirley", "responsable01");

    private final UsuarioJpaRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        String hash = passwordEncoder.encode(DEV_PASSWORD);
        for (String username : SEED_USERNAMES) {
            usuarioRepository.findByEmailOrUsername(username).ifPresent(user -> {
                if (!passwordEncoder.matches(DEV_PASSWORD, user.getPasswordHash())) {
                    user.setPasswordHash(hash);
                    usuarioRepository.save(user);
                    log.info("Dev: contraseña seed actualizada para '{}'", username);
                }
            });
        }
    }
}
