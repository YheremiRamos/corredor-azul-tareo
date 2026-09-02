package pe.ctarequipa.tareo.application.port.out;

import pe.ctarequipa.tareo.application.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> findByEmailOrUsername(String emailOrUsername);
    Optional<Usuario> findById(Long id);
    List<Usuario> findAll();
}
