package pe.ctarequipa.tareo.infrastructure.security;

import java.util.List;

public record JwtPrincipal(String userId, String nombre, String rol, List<String> areaIds) {}
