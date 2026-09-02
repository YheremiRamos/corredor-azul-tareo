package pe.ctarequipa.tareo.application.port.in;

import pe.ctarequipa.tareo.application.model.ReporteItem;

import java.util.List;

public interface ListarReportesUseCase {
    List<ReporteItem> listar();
}
