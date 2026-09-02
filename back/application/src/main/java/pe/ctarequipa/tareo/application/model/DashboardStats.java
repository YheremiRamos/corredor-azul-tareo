package pe.ctarequipa.tareo.application.model;

public record DashboardStats(
        long colaboradoresActivos,
        long periodosAbiertos,
        long tareosPendientes,
        long asistenciasHoy
) {}
