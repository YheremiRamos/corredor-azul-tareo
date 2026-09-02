package pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public record GuardarAsistenciasRequest(int quincena, List<AsistenciaDto> asistencias) {
    public record AsistenciaDto(
            Long tareoColaboradorId,
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
