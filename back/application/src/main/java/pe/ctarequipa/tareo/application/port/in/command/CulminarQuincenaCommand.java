package pe.ctarequipa.tareo.application.port.in.command;

public record CulminarQuincenaCommand(Long tareoId, int quincena, String usuarioId) {}
