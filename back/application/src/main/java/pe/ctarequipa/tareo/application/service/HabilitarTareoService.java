package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.port.in.HabilitarTareoUseCase;
import pe.ctarequipa.tareo.application.port.in.command.HabilitarTareoCommand;
import pe.ctarequipa.tareo.application.port.out.ColaboradorRepository;
import pe.ctarequipa.tareo.application.port.out.PeriodoRepository;
import pe.ctarequipa.tareo.application.port.out.TareoRepository;
import pe.ctarequipa.tareo.domain.model.Colaborador;
import pe.ctarequipa.tareo.domain.model.Periodo;
import pe.ctarequipa.tareo.domain.model.Tareo;
import pe.ctarequipa.tareo.domain.model.TareoColaborador;

import java.util.ArrayList;
import java.util.List;

public class HabilitarTareoService implements HabilitarTareoUseCase {

    private final TareoRepository tareoRepository;
    private final PeriodoRepository periodoRepository;
    private final ColaboradorRepository colaboradorRepository;

    public HabilitarTareoService(
            TareoRepository tareoRepository,
            PeriodoRepository periodoRepository,
            ColaboradorRepository colaboradorRepository) {
        this.tareoRepository = tareoRepository;
        this.periodoRepository = periodoRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    public Tareo habilitar(HabilitarTareoCommand command) {
        Periodo periodo = periodoRepository.findById(command.periodoId())
                .orElseThrow(() -> new IllegalArgumentException("Periodo no encontrado"));

        Tareo tareo = tareoRepository.findByPeriodoAreaSubarea(
                        command.periodoId(), command.areaId(), command.subareaId())
                .orElseGet(() -> new Tareo(
                        null,
                        command.periodoId(),
                        command.areaId(),
                        command.subareaId(),
                        false,
                        "PENDIENTE",
                        "PENDIENTE",
                        null,
                        null,
                        null,
                        null
                ));

        if (tareo.habilitado()) {
            return tareo;
        }

        Tareo habilitado = new Tareo(
                tareo.id(),
                tareo.periodoId(),
                tareo.areaId(),
                tareo.subareaId(),
                true,
                "EN_PROCESO",
                tareo.estadoQ2(),
                tareo.fechaEnvioQ1(),
                tareo.fechaEnvioQ2(),
                tareo.usuarioEnvioQ1(),
                tareo.usuarioEnvioQ2()
        );
        Tareo guardado = tareoRepository.save(habilitado);

        List<Colaborador> colaboradores = command.subareaId() != null
                ? colaboradorRepository.findByAreaIdAndSubareaId(command.areaId(), command.subareaId())
                : colaboradorRepository.findByAreaId(command.areaId());

        List<TareoColaborador> snapshots = new ArrayList<>();
        for (Colaborador c : colaboradores) {
            if (c.activoEn(periodo.fechaInicio()) || c.activoEn(periodo.fechaFin())) {
                snapshots.add(new TareoColaborador(
                        null,
                        guardado.id(),
                        c.id(),
                        c.codigo(),
                        c.dni(),
                        c.nombres(),
                        c.tipoTrabajadorId(),
                        c.areaId(),
                        c.subareaId(),
                        c.cargo()
                ));
            }
        }
        tareoRepository.saveColaboradores(snapshots);
        return guardado;
    }
}
