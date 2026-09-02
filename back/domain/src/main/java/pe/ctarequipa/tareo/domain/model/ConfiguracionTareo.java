package pe.ctarequipa.tareo.domain.model;

import java.time.LocalDate;

public record ConfiguracionTareo(
        Long id,
        int diaInicioMes,
        int diaFinMes,
        int diaCorteQuincena1,
        int diaInicioQuincena2
) {
    public static ConfiguracionTareo porDefecto() {
        return new ConfiguracionTareo(1L, 22, 21, 7, 8);
    }
}
