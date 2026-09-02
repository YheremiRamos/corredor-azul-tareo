package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.model.Usuario;
import pe.ctarequipa.tareo.application.port.in.ListarUsuariosUseCase;
import pe.ctarequipa.tareo.application.port.out.UsuarioRepository;

import java.util.List;

public class ListarUsuariosService implements ListarUsuariosUseCase {

    private final UsuarioRepository usuarioRepository;

    public ListarUsuariosService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }
}
