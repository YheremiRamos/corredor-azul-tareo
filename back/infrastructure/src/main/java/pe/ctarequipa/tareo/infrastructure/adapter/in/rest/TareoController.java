package pe.ctarequipa.tareo.infrastructure.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.ctarequipa.tareo.application.model.MatrizTareo;
import pe.ctarequipa.tareo.application.port.in.*;
import pe.ctarequipa.tareo.application.port.in.command.*;
import pe.ctarequipa.tareo.domain.model.Asistencia;
import pe.ctarequipa.tareo.domain.model.Tareo;
import pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto.*;
import pe.ctarequipa.tareo.infrastructure.security.AreaAuthorizationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tareo")
@RequiredArgsConstructor
public class TareoController {

    private final ListarTareosUseCase listarTareosUseCase;
    private final HabilitarTareoUseCase habilitarTareoUseCase;
    private final ConsultarMatrizUseCase consultarMatrizUseCase;
    private final GuardarAsistenciasUseCase guardarAsistenciasUseCase;
    private final CulminarQuincenaUseCase culminarQuincenaUseCase;
    private final AreaAuthorizationService areaAuthorizationService;

    @GetMapping
    public List<TareoResponse> listar() {
        return listarTareosUseCase.listar().stream().map(this::toResponse).toList();
    }

    @PostMapping("/habilitar")
    public TareoResponse habilitar(@RequestBody HabilitarTareoRequest request) {
        areaAuthorizationService.verificarAcceso(request.areaId());
        Tareo tareo = habilitarTareoUseCase.habilitar(new HabilitarTareoCommand(
                request.periodoId(),
                request.areaId(),
                request.subareaId(),
                areaAuthorizationService.currentUserId()
        ));
        return toResponse(tareo);
    }

    @GetMapping("/{id}/matriz")
    public MatrizResponse matriz(@PathVariable Long id, @RequestParam int quincena) {
        MatrizTareo matriz = consultarMatrizUseCase.consultar(id, quincena);
        return toMatrizResponse(matriz);
    }

    @PutMapping("/{id}/asistencias")
    public void guardarAsistencias(
            @PathVariable Long id,
            @RequestBody GuardarAsistenciasRequest request) {
        List<AsistenciaItemCommand> items = request.asistencias().stream()
                .map(a -> new AsistenciaItemCommand(
                        a.tareoColaboradorId(),
                        a.periodoDiaId(),
                        a.categoriaCodigo(),
                        a.turnoId(),
                        a.bonificacionNocturna(),
                        a.heTotal(),
                        a.he25(),
                        a.he30(),
                        a.observacion()
                ))
                .toList();
        guardarAsistenciasUseCase.guardar(new GuardarAsistenciasCommand(
                id, request.quincena(), areaAuthorizationService.currentUserId(), items));
    }

    @PostMapping("/{id}/culminar")
    public TareoResponse culminar(@PathVariable Long id, @RequestParam int quincena) {
        Tareo tareo = culminarQuincenaUseCase.culminar(new CulminarQuincenaCommand(
                id, quincena, areaAuthorizationService.currentUserId()));
        return toResponse(tareo);
    }

    private TareoResponse toResponse(Tareo t) {
        String estado = t.estadoQ1() + "/" + t.estadoQ2();
        return new TareoResponse(
                String.valueOf(t.id()),
                String.valueOf(t.periodoId()),
                t.areaId(),
                estado
        );
    }

    private MatrizResponse toMatrizResponse(MatrizTareo matriz) {
        List<MatrizResponse.DiaDto> dias = matriz.dias().stream()
                .map(d -> new MatrizResponse.DiaDto(d.id(), d.fecha(), d.orden(), d.quincena(), d.diaSemana()))
                .toList();
        List<MatrizResponse.FilaDto> filas = matriz.filas().stream()
                .map(f -> new MatrizResponse.FilaDto(
                        f.colaborador().id(),
                        f.colaborador().colaboradorId(),
                        f.colaborador().codigoSnapshot(),
                        f.colaborador().dniSnapshot(),
                        f.colaborador().nombresSnapshot(),
                        f.asistencias().stream().map(this::toAsistenciaDto).toList()
                ))
                .toList();
        return new MatrizResponse(matriz.tareoId(), matriz.quincena(), matriz.bloqueada(), dias, filas);
    }

    private MatrizResponse.AsistenciaDto toAsistenciaDto(Asistencia a) {
        return new MatrizResponse.AsistenciaDto(
                a.id(),
                a.periodoDiaId(),
                a.categoriaCodigo(),
                a.turnoId(),
                a.bonificacionNocturna(),
                a.heTotal(),
                a.he25(),
                a.he30(),
                a.observacion()
        );
    }
}
