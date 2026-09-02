package pe.ctarequipa.tareo.domain.vo;

public record Dni(String valor) {
    public Dni {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("DNI/CE no puede estar vacio");
        }
        String normalized = valor.trim().toUpperCase();
        if (!normalized.matches("\\d{8}") && !normalized.matches("[A-Z]{2}\\d{6,12}")) {
            throw new IllegalArgumentException("DNI/CE invalido: " + valor);
        }
        valor = normalized;
    }
}
