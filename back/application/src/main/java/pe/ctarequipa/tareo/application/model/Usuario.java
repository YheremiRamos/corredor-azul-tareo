package pe.ctarequipa.tareo.application.model;

import java.util.List;

public record Usuario(
        Long id,
        String username,
        String passwordHash,
        String nombre,
        String email,
        String rolId,
        boolean activo,
        List<String> areaIds,
        List<String> subareaIds
) {}
