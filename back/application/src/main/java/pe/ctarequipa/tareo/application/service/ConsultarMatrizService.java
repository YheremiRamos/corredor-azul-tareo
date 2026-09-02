package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.model.MatrizTareo;
import pe.ctarequipa.tareo.application.port.in.ConsultarMatrizUseCase;
import pe.ctarequipa.tareo.application.port.out.PeriodoRepository;
import pe.ctarequipa.tareo.application.port.out.TareoRepository;
import pe.ctarequipa.tareo.domain.model.Asistencia;
import pe.ctarequipa.tareo.domain.model.PeriodoDia;
import pe.ctarequipa.tareo.domain.model.Tareo;
import pe.ctarequipa.tareo.domain.model.TareoColaborador;

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

        Map<Long, List<Asistencia>> porColaborador = asistencias.stream()
                .collect(Collectors.groupingBy(Asistencia::tareoColaboradorId));

        List<MatrizTareo.FilaMatriz> filas = new ArrayList<>();
        for (TareoColaborador col : colaboradores) {
            filas.add(new MatrizTareo.FilaMatriz(col, porColaborador.getOrDefault(col.id(), List.of())));
        }

        return new MatrizTareo(tareoId, quincena, tareo.quincenaBloqueada(quincena), dias, filas);
    }
}
