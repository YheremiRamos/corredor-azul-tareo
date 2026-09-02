package pe.ctarequipa.tareo.domain.model;

import java.time.LocalDateTime;

public record Tareo(
        Long id,
        Long periodoId,
        String areaId,
        String subareaId,
        boolean habilitado,
        String estadoQ1,
        String estadoQ2,
        LocalDateTime fechaEnvioQ1,
        LocalDateTime fechaEnvioQ2,
        String usuarioEnvioQ1,
        String usuarioEnvioQ2
) {
    public boolean quincenaBloqueada(int quincena) {
        String estado = quincena == 1 ? estadoQ1 : estadoQ2;
        return "ENVIADO".equals(estado) || "CONSOLIDADO".equals(estado);
    }
}
