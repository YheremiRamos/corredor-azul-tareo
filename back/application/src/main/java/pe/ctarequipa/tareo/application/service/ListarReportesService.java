package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.model.ReporteItem;
import pe.ctarequipa.tareo.application.port.in.ListarReportesUseCase;

import java.util.List;

public class ListarReportesService implements ListarReportesUseCase {

    @Override
    public List<ReporteItem> listar() {
        return List.of(
                new ReporteItem("asistencias", "Reporte de Asistencias"),
                new ReporteItem("horas-extras", "Reporte de Horas Extras"),
                new ReporteItem("consolidado", "Reporte Consolidado")
        );
    }
}
