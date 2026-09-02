package pe.ctarequipa.tareo.domain.service;

import org.junit.jupiter.api.Test;
import pe.ctarequipa.tareo.domain.model.ConfiguracionTareo;
import pe.ctarequipa.tareo.domain.model.PeriodoDia;
import pe.ctarequipa.tareo.domain.vo.RangoFechas;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeriodoCalculatorTest {

    private final ConfiguracionTareo config = ConfiguracionTareo.porDefecto();

    @Test
    void calcularRango_febrero2026() {
        RangoFechas rango = PeriodoCalculator.calcularRango(2026, 2, config);
        assertEquals(LocalDate.of(2026, 1, 22), rango.inicio());
        assertEquals(LocalDate.of(2026, 2, 21), rango.fin());
    }

    @Test
    void generarDias_asignaQuincenas() {
        RangoFechas rango = PeriodoCalculator.calcularRango(2026, 2, config);
        List<PeriodoDia> dias = PeriodoCalculator.generarDias(rango, config);
        assertFalse(dias.isEmpty());
        assertTrue(dias.stream().anyMatch(d -> d.quincena() == 1));
        assertTrue(dias.stream().anyMatch(d -> d.quincena() == 2));
        assertEquals(1, dias.getFirst().orden());
    }

    @Test
    void nombreMes_retornaEspanol() {
        String nombre = PeriodoCalculator.nombreMes(3);
        assertTrue(nombre.toUpperCase().contains("MARZO"));
    }
}
