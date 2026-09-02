package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.model.MatrizTareo;
import pe.ctarequipa.tareo.application.port.in.ConsultarMatrizUseCase;
import pe.ctarequipa.tareo.application.port.out.PeriodoRepository;
import pe.ctarequipa.tareo.application.port.out.TareoRepository;
import pe.ctarequipa.tareo.domain.model.Asistencia;
import pe.ctarequipa.tareo.domain.model.PeriodoDia;
import pe.ctarequipa.tareo.domain.model.Tareo;
import pe.ctarequipa.tareo.domain.model.TareoColaborador;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsultarMatrizService implements ConsultarMatrizUseCase {

    private final TareoRepository tareoRepository;
    private final PeriodoRepository periodoRepository;

    public ConsultarMatrizService(TareoRepository tareoRepository, PeriodoRepository periodoRepository) {
        this.tareoRepository = tareoRepository;
        this.periodoRepository = periodoRepository;
    }

    @Override
    public MatrizTareo consultar(Long tareoId, int quincena) {
        Tareo tareo = tareoRepository.findById(tareoId)
                .orElseThrow(() -> new IllegalArgumentException("Tareo no encontrado"));

        List<PeriodoDia> dias = periodoRepository.findDiasByPeriodoId(tareo.periodoId()).stream()
                .filter(d -> d.quincena() == quincena)
                .toList();

        List<TareoColaborador> colaboradores = tareoRepository.findColaboradoresByTareoId(tareoId);
        List<Asistencia> asistencias = tareoRepository.findAsistenciasByTareoIdAndQuincena(tareoId, quincena);

        // Indexa asistencias existentes por (colaborador, dia) para armar la matriz completa.
        Map<String, Asistencia> existentes = asistencias.stream()
                .collect(Collectors.toMap(
                        a -> a.tareoColaboradorId() + ":" + a.periodoDiaId(),
                        a -> a,
                        (a, b) -> a));

        List<MatrizTareo.FilaMatriz> filas = new ArrayList<>();
        for (TareoColaborador col : colaboradores) {
            List<Asistencia> celdas = new ArrayList<>();
            for (PeriodoDia dia : dias) {
                Asistencia existente = existentes.get(col.id() + ":" + dia.id());
                if (existente != null) {
                    celdas.add(existente);
                } else {
                    // Celda vacia (aun sin registrar) para que sea visible y editable.
                    celdas.add(new Asistencia(
                            null, col.id(), dia.id(), null, null,
                            false, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, null));
                }
            }
            filas.add(new MatrizTareo.FilaMatriz(col, celdas));
        }

        return new MatrizTareo(tareoId, quincena, tareo.quincenaBloqueada(quincena), dias, filas);
    }
}
