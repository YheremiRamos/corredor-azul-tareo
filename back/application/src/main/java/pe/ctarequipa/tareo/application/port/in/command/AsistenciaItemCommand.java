package pe.ctarequipa.tareo.application.port.in.command;

import java.math.BigDecimal;

public record AsistenciaItemCommand(
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
