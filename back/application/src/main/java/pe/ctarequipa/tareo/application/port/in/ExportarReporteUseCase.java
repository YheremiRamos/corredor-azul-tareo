package pe.ctarequipa.tareo.application.port.in;

public interface ExportarReporteUseCase {
    byte[] exportar(String formato, Long periodoId, String areaId);
}
