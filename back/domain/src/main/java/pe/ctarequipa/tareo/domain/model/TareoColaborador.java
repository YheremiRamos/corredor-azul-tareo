package pe.ctarequipa.tareo.domain.model;

public record TareoColaborador(
        Long id,
        Long tareoId,
        Long colaboradorId,
        String codigoSnapshot,
        String dniSnapshot,
        String nombresSnapshot,
        String tipoSnapshot,
        String areaSnapshot,
        String subareaSnapshot,
        String cargoSnapshot
) {}
