package pe.ctarequipa.tareo.infrastructure.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.ctarequipa.tareo.application.port.in.ListarAreasUseCase;
import pe.ctarequipa.tareo.domain.model.Area;
import pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto.AreaResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/areas")
@RequiredArgsConstructor
public class AreaController {

    private final ListarAreasUseCase listarAreasUseCase;

    @GetMapping
    public List<AreaResponse> listar() {
        return listarAreasUseCase.listar().stream().map(this::toResponse).toList();
    }

    private AreaResponse toResponse(Area area) {
        return new AreaResponse(area.id(), area.nombre());
    }
}
