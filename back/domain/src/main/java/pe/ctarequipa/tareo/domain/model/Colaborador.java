package pe.ctarequipa.tareo.domain.model;

import java.time.LocalDate;

public record Colaborador(
        Long id,
        String codigo,
        String dni,
        String nombres,
        String tipoTrabajadorId,
        String areaId,
        String subareaId,
        String cargo,
        LocalDate fechaIngreso,
        LocalDate fechaCese,
        boolean activo,
        boolean esJefatura
) {
    public boolean activoEn(LocalDate fecha) {
        if (!activo) return false;
        if (fechaIngreso != null && fecha.isBefore(fechaIngreso)) return false;
        return fechaCese == null || !fecha.isAfter(fechaCese);
    }
}
