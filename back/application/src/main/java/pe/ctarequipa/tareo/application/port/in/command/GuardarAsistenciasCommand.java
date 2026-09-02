package pe.ctarequipa.tareo.application.port.in.command;

import java.util.List;

public record GuardarAsistenciasCommand(
        Long tareoId,
        int quincena,
        String usuarioId,
        List<AsistenciaItemCommand> asistencias
) {}
