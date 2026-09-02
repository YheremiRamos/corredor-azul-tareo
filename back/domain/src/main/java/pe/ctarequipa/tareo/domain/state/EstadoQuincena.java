package pe.ctarequipa.tareo.domain.state;

import java.time.LocalDateTime;

public sealed interface EstadoQuincena
        permits EstadoQuincena.Pendiente,
                EstadoQuincena.EnProceso,
                EstadoQuincena.Completado,
                EstadoQuincena.Enviado,
                EstadoQuincena.Consolidado,
                EstadoQuincena.Reabierto {

    record Pendiente() implements EstadoQuincena {}

    record EnProceso(LocalDateTime inicioRegistro) implements EstadoQuincena {}

    record Completado(LocalDateTime fechaCompletado) implements EstadoQuincena {}

    record Enviado(LocalDateTime fechaEnvio, String usuarioEnvio) implements EstadoQuincena {}

    record Consolidado(LocalDateTime fechaConsolidacion) implements EstadoQuincena {}

    record Reabierto(String motivo, LocalDateTime fechaReapertura) implements EstadoQuincena {}

    static String etiqueta(EstadoQuincena estado) {
        return switch (estado) {
            case Pendiente() -> "PENDIENTE";
            case EnProceso e -> "EN_PROCESO";
            case Completado c -> "COMPLETADO";
            case Enviado e -> "ENVIADO";
            case Consolidado c -> "CONSOLIDADO";
            case Reabierto r -> "REABIERTO: " + r.motivo();
        };
    }
}
