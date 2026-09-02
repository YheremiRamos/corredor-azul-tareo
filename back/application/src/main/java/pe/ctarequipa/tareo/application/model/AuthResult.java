package pe.ctarequipa.tareo.application.model;

public record AuthResult(String token, Long userId, String nombre, String email, String rol) {}
