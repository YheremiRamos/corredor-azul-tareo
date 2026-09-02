package pe.ctarequipa.tareo.application.port.in;

import pe.ctarequipa.tareo.application.model.Usuario;

import java.util.List;

public interface ListarUsuariosUseCase {
    List<Usuario> listar();
}
