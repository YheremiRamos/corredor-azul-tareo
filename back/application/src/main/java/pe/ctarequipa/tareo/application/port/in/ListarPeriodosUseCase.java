package pe.ctarequipa.tareo.application.port.in;

import pe.ctarequipa.tareo.domain.model.Periodo;

import java.util.List;

public interface ListarPeriodosUseCase {
    List<Periodo> listar();
}
