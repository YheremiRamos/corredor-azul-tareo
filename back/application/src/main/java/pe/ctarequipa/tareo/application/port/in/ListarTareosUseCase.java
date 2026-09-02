package pe.ctarequipa.tareo.application.port.in;

import pe.ctarequipa.tareo.domain.model.Tareo;

import java.util.List;

public interface ListarTareosUseCase {
    List<Tareo> listar();
}
