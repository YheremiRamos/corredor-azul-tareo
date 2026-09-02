package pe.ctarequipa.tareo.domain.model;

import java.math.BigDecimal;

public record Asistencia(
        Long id,
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
