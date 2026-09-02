package pe.ctarequipa.tareo.infrastructure.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.ctarequipa.tareo.application.port.in.ListarUsuariosUseCase;
import pe.ctarequipa.tareo.application.model.Usuario;
import pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto.UsuarioResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final ListarUsuariosUseCase listarUsuariosUseCase;

    @GetMapping
    public List<UsuarioResponse> listar() {
        return listarUsuariosUseCase.listar().stream().map(this::toResponse).toList();
    }

    private UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(String.valueOf(u.id()), u.email(), u.rolId());
    }
}
