package pe.ctarequipa.tareo.infrastructure.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.ctarequipa.tareo.application.port.in.CrearPeriodoUseCase;
import pe.ctarequipa.tareo.application.port.in.ListarPeriodosUseCase;
import pe.ctarequipa.tareo.application.port.in.command.CrearPeriodoCommand;
import pe.ctarequipa.tareo.domain.model.Periodo;
import pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto.CrearPeriodoRequest;
import pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto.PeriodoResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/periodos")
@RequiredArgsConstructor
public class PeriodoController {

    private final ListarPeriodosUseCase listarPeriodosUseCase;
    private final CrearPeriodoUseCase crearPeriodoUseCase;

    @GetMapping
    public List<PeriodoResponse> listar() {
        return listarPeriodosUseCase.listar().stream().map(this::toResponse).toList();
    }

    @PostMapping
    public PeriodoResponse crear(@RequestBody CrearPeriodoRequest request) {
        Periodo periodo = crearPeriodoUseCase.crear(new CrearPeriodoCommand(request.anio(), request.mes()));
        return toResponse(periodo);
    }

    private PeriodoResponse toResponse(Periodo p) {
        return new PeriodoResponse(String.valueOf(p.id()), p.nombre(), p.estado());
    }
}
