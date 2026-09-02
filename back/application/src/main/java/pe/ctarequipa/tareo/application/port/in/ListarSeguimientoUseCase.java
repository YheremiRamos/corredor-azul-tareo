package pe.ctarequipa.tareo.application.port.in;

import pe.ctarequipa.tareo.application.model.SeguimientoItem;

import java.util.List;

public interface ListarSeguimientoUseCase {
    List<SeguimientoItem> listar();
}
