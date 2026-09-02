package pe.ctarequipa.tareo.application.port.in.command;

public record HabilitarTareoCommand(
        Long periodoId,
        String areaId,
        String subareaId,
        String usuarioId
) {}
