package pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record MatrizResponse(
        Long tareoId,
        int quincena,
        boolean bloqueada,
        List<DiaDto> dias,
        List<FilaDto> filas
) {
    public record DiaDto(Long id, LocalDate fecha, int orden, int quincena, String diaSemana) {}

    public record FilaDto(
            Long tareoColaboradorId,
            Long colaboradorId,
            String codigo,
            String dni,
            String nombres,
            List<AsistenciaDto> asistencias
    ) {}

    public record AsistenciaDto(
            Long id,
            Long periodoDiaId,
            String categoriaCodigo,
            String turnoId,
            boolean bonificacionNocturna,
            BigDecimal heTotal,
            BigDecimal he25,
            BigDecimal he30,
            String observacion
    ) {}
}
