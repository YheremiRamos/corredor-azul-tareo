package pe.ctarequipa.tareo.infrastructure.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.ctarequipa.tareo.application.port.in.ListarColaboradoresUseCase;
import pe.ctarequipa.tareo.domain.model.Colaborador;
import pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto.ColaboradorResponse;
import pe.ctarequipa.tareo.infrastructure.security.AreaAuthorizationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/colaboradores")
@RequiredArgsConstructor
public class ColaboradorController {

    private final ListarColaboradoresUseCase listarColaboradoresUseCase;
    private final AreaAuthorizationService areaAuthorizationService;

    @GetMapping
    public List<ColaboradorResponse> listar(@RequestParam(required = false) String areaId) {
        if (areaId != null) {
            areaAuthorizationService.verificarAcceso(areaId);
        }
        return listarColaboradoresUseCase.listar(areaId).stream().map(this::toResponse).toList();
    }

    private ColaboradorResponse toResponse(Colaborador c) {
        return new ColaboradorResponse(String.valueOf(c.id()), c.dni(), c.nombres());
    }
}
