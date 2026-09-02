package pe.ctarequipa.tareo.application.port.out;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Reloj {
    LocalDateTime ahora();
    LocalDate hoy();
}
