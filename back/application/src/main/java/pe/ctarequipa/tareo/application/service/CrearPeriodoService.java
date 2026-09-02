package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.port.in.CrearPeriodoUseCase;
import pe.ctarequipa.tareo.application.port.in.command.CrearPeriodoCommand;
import pe.ctarequipa.tareo.application.port.out.ConfiguracionRepository;
import pe.ctarequipa.tareo.application.port.out.PeriodoRepository;
import pe.ctarequipa.tareo.domain.model.ConfiguracionTareo;
import pe.ctarequipa.tareo.domain.model.Periodo;
import pe.ctarequipa.tareo.domain.model.PeriodoDia;
import pe.ctarequipa.tareo.domain.service.PeriodoCalculator;
import pe.ctarequipa.tareo.domain.vo.RangoFechas;

import java.util.List;

public class CrearPeriodoService implements CrearPeriodoUseCase {

    private final PeriodoRepository periodoRepository;
    private final ConfiguracionRepository configuracionRepository;

    public CrearPeriodoService(PeriodoRepository periodoRepository, ConfiguracionRepository configuracionRepository) {
        this.periodoRepository = periodoRepository;
        this.configuracionRepository = configuracionRepository;
    }

    @Override
    public Periodo crear(CrearPeriodoCommand command) {
        periodoRepository.findByAnioMes(command.anio(), command.mes())
                .ifPresent(p -> { throw new IllegalArgumentException("Periodo ya existe para " + command.anio() + "/" + command.mes()); });

        ConfiguracionTareo config = configuracionRepository.findActiva()
                .orElse(ConfiguracionTareo.porDefecto());
        RangoFechas rango = PeriodoCalculator.calcularRango(command.anio(), command.mes(), config);
        String nombre = PeriodoCalculator.nombreMes(command.mes()) + " " + command.anio();
        List<PeriodoDia> dias = PeriodoCalculator.generarDias(rango, config);

        Periodo periodo = new Periodo(
                null,
                command.anio(),
                command.mes(),
                nombre,
                rango.inicio(),
                rango.fin(),
                "ACTIVO",
                List.of()
        );
        Periodo guardado = periodoRepository.save(periodo);
        periodoRepository.saveDias(guardado.id(), dias);
        List<PeriodoDia> diasGuardados = periodoRepository.findDiasByPeriodoId(guardado.id());
        return new Periodo(
                guardado.id(),
                guardado.anio(),
                guardado.mes(),
                guardado.nombre(),
                guardado.fechaInicio(),
                guardado.fechaFin(),
                guardado.estado(),
                diasGuardados
        );
    }
}
