package pe.ctarequipa.tareo.domain.service;

import pe.ctarequipa.tareo.domain.model.ConfiguracionTareo;
import pe.ctarequipa.tareo.domain.model.PeriodoDia;
import pe.ctarequipa.tareo.domain.vo.RangoFechas;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class PeriodoCalculator {

    private PeriodoCalculator() {}

    public static RangoFechas calcularRango(int anio, int mes, ConfiguracionTareo config) {
        LocalDate fin = LocalDate.of(anio, mes, config.diaFinMes());
        LocalDate inicio = fin.minusMonths(1).withDayOfMonth(config.diaInicioMes());
        return new RangoFechas(inicio, fin);
    }

    public static String nombreMes(int mes) {
        return Month.of(mes).getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
    }

    public static List<PeriodoDia> generarDias(RangoFechas rango, ConfiguracionTareo config) {
        List<PeriodoDia> dias = new ArrayList<>();
        int orden = 1;
        for (LocalDate fecha : rango.diasEntre()) {
            int quincena = fecha.getDayOfMonth() <= config.diaCorteQuincena1() ? 1 : 2;
            String diaSemana = abreviaturaDia(fecha.getDayOfWeek());
            dias.add(new PeriodoDia(null, fecha, orden++, quincena, diaSemana));
        }
        return dias;
    }

    private static String abreviaturaDia(DayOfWeek day) {
        return switch (day) {
            case MONDAY -> "L";
            case TUESDAY, WEDNESDAY -> "M";
            case THURSDAY -> "J";
            case FRIDAY -> "V";
            case SATURDAY -> "S";
            case SUNDAY -> "D";
        };
    }
}
