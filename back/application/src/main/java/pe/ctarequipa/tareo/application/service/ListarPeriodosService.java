package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.port.in.ListarPeriodosUseCase;
import pe.ctarequipa.tareo.application.port.out.PeriodoRepository;
import pe.ctarequipa.tareo.domain.model.Periodo;

import java.util.List;

public class ListarPeriodosService implements ListarPeriodosUseCase {

    private final PeriodoRepository periodoRepository;

    public ListarPeriodosService(PeriodoRepository periodoRepository) {
        this.periodoRepository = periodoRepository;
    }

    @Override
    public List<Periodo> listar() {
        return periodoRepository.findAll();
    }
}
