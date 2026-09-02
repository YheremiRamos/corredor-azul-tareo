package pe.ctarequipa.tareo.domain.service;

import java.math.BigDecimal;

public final class ValidadorHorasExtras {

    private ValidadorHorasExtras() {}

    public static void validar(BigDecimal total, BigDecimal he25, BigDecimal he30) {
        BigDecimal t = total == null ? BigDecimal.ZERO : total;
        BigDecimal h25 = he25 == null ? BigDecimal.ZERO : he25;
        BigDecimal h30 = he30 == null ? BigDecimal.ZERO : he30;
        if (h25.compareTo(BigDecimal.ZERO) < 0 || h30.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Horas extras no pueden ser negativas");
        }
        if (h25.add(h30).compareTo(t) > 0) {
            throw new IllegalArgumentException("HE 25% + HE 30% no puede superar HE total");
        }
    }
}
