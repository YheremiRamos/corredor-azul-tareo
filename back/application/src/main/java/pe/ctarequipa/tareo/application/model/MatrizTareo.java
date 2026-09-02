package pe.ctarequipa.tareo.application.model;

import pe.ctarequipa.tareo.domain.model.Asistencia;
import pe.ctarequipa.tareo.domain.model.PeriodoDia;
import pe.ctarequipa.tareo.domain.model.TareoColaborador;

import java.util.List;

public record MatrizTareo(
        Long tareoId,
        int quincena,
        boolean bloqueada,
        List<PeriodoDia> dias,
        List<FilaMatriz> filas
) {
    public record FilaMatriz(TareoColaborador colaborador, List<Asistencia> asistencias) {}
}
