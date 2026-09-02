package pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto;

public record DashboardStatsResponse(
        long colaboradoresActivos,
        long periodosAbiertos,
        long tareosPendientes,
        long asistenciasHoy
) {}
