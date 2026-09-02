package pe.ctarequipa.tareo.infrastructure.adapter.out.time;

import org.springframework.stereotype.Component;
import pe.ctarequipa.tareo.application.port.out.Reloj;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class SystemReloj implements Reloj {

    @Override
    public LocalDateTime ahora() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDate hoy() {
        return LocalDate.now();
    }
}
