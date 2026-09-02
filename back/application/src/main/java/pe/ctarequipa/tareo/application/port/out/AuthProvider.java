package pe.ctarequipa.tareo.application.port.out;

import pe.ctarequipa.tareo.application.model.Usuario;

import java.util.Optional;

public interface AuthProvider {
    Optional<Usuario> autenticar(String emailOrUsername, String password);
}
