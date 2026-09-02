package pe.ctarequipa.tareo.infrastructure.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.ctarequipa.tareo.application.port.in.ListarSeguimientoUseCase;
import pe.ctarequipa.tareo.application.model.SeguimientoItem;
import pe.ctarequipa.tareo.domain.model.Tareo;
import pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto.SeguimientoResponse;
import pe.ctarequipa.tareo.infrastructure.security.AreaAuthorizationService;
import pe.ctarequipa.tareo.infrastructure.service.ConsolidacionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seguimiento")
@RequiredArgsConstructor
public class SeguimientoController {

    private final ListarSeguimientoUseCase listarSeguimientoUseCase;
    private final ConsolidacionService consolidacionService;
    private final AreaAuthorizationService areaAuthorizationService;

    @GetMapping
    public List<SeguimientoResponse> listar() {
        return listarSeguimientoUseCase.listar().stream().map(this::toResponse).toList();
    }

    @PostMapping("/{tareoId}/consolidar")
    public SeguimientoResponse consolidar(
            @PathVariable Long tareoId,
            @RequestParam int quincena) {
        Tareo tareo = consolidacionService.consolidarQuincena(
                tareoId,
                quincena,
                areaAuthorizationService.currentUserIdLong(),
                areaAuthorizationService.currentUserNombre());
        return new SeguimientoResponse(String.valueOf(tareo.id()), "Consolidado Q" + quincena);
    }

    private SeguimientoResponse toResponse(SeguimientoItem item) {
        return new SeguimientoResponse(item.id(), item.descripcion());
    }
}
