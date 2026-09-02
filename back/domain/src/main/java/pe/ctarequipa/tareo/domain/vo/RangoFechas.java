package pe.ctarequipa.tareo.domain.vo;

import java.time.LocalDate;
import java.util.List;

public record RangoFechas(LocalDate inicio, LocalDate fin) {
    public RangoFechas {
        if (inicio == null || fin == null) {
            throw new IllegalArgumentException("Fechas requeridas");
        }
        if (inicio.isAfter(fin)) {
            throw new IllegalArgumentException("Inicio debe ser anterior o igual a fin");
        }
    }

    public List<LocalDate> diasEntre() {
        return inicio.datesUntil(fin.plusDays(1)).toList();
    }
}
