package pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto;

public record LoginResponse(
        String token,
        String userId,
        String nombre,
        String email,
        String rol
) {}
