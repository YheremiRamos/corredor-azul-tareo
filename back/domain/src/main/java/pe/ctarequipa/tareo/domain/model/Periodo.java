package pe.ctarequipa.tareo.domain.model;

import java.time.LocalDate;
import java.util.List;

public record Periodo(
        Long id,
        int anio,
        int mes,
        String nombre,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String estado,
        List<PeriodoDia> dias
) {}
