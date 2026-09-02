package pe.ctarequipa.tareo.domain.model;

import java.time.LocalDate;

public record PeriodoDia(
        Long id,
        LocalDate fecha,
        int orden,
        int quincena,
        String diaSemana
) {}
