package pe.ctarequipa.tareo.application.port.in;

import pe.ctarequipa.tareo.domain.model.Colaborador;

import java.util.List;

public interface ListarColaboradoresUseCase {
    List<Colaborador> listar(String areaId);
}
