package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.ctarequipa.tareo.application.model.Usuario;
import pe.ctarequipa.tareo.application.port.out.UsuarioRepository;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.mapper.UsuarioMapper;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository.UsuarioJpaRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioJpaAdapter implements UsuarioRepository {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Optional<Usuario> findByEmailOrUsername(String emailOrUsername) {
        return usuarioJpaRepository.findByEmailOrUsername(emailOrUsername).map(usuarioMapper::toDomain);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioJpaRepository.findById(id).map(usuarioMapper::toDomain);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioMapper.toDomainList(usuarioJpaRepository.findAllWithAreas());
    }
}
