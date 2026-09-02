package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.port.in.CulminarQuincenaUseCase;
import pe.ctarequipa.tareo.application.port.in.command.CulminarQuincenaCommand;
import pe.ctarequipa.tareo.application.port.out.Notificador;
import pe.ctarequipa.tareo.application.port.out.PeriodoRepository;
import pe.ctarequipa.tareo.application.port.out.Reloj;
import pe.ctarequipa.tareo.application.port.out.TareoRepository;
import pe.ctarequipa.tareo.domain.exception.TareoBloqueadoException;
import pe.ctarequipa.tareo.domain.model.Asistencia;
import pe.ctarequipa.tareo.domain.model.PeriodoDia;
import pe.ctarequipa.tareo.domain.model.Tareo;
import pe.ctarequipa.tareo.domain.model.TareoColaborador;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CulminarQuincenaService implements CulminarQuincenaUseCase {

    private final TareoRepository tareoRepository;
    private final PeriodoRepository periodoRepository;
    private final Reloj reloj;
    private final Notificador notificador;
    private final String rrhhEmail;

    public CulminarQuincenaService(
            TareoRepository tareoRepository,
            PeriodoRepository periodoRepository,
            Reloj reloj,
            Notificador notificador,
            String rrhhEmail) {
        this.tareoRepository = tareoRepository;
        this.periodoRepository = periodoRepository;
        this.reloj = reloj;
        this.notificador = notificador;
        this.rrhhEmail = rrhhEmail;
    }

    @Override
    public Tareo culminar(CulminarQuincenaCommand command) {
        Tareo tareo = tareoRepository.findById(command.tareoId())
                .orElseThrow(() -> new IllegalArgumentException("Tareo no encontrado"));
        if (tareo.quincenaBloqueada(command.quincena())) {
            throw new TareoBloqueadoException("La quincena ya fue enviada");
        }

        validarCompletitud(command.tareoId(), tareo.periodoId(), command.quincena());

        Tareo actualizado;
        if (command.quincena() == 1) {
            actualizado = new Tareo(
                    tareo.id(),
                    tareo.periodoId(),
                    tareo.areaId(),
                    tareo.subareaId(),
                    tareo.habilitado(),
                    "ENVIADO",
                    tareo.estadoQ2(),
                    reloj.ahora(),
                    tareo.fechaEnvioQ2(),
                    command.usuarioId(),
                    tareo.usuarioEnvioQ2()
            );
        } else {
            actualizado = new Tareo(
                    tareo.id(),
                    tareo.periodoId(),
                    tareo.areaId(),
                    tareo.subareaId(),
                    tareo.habilitado(),
                    tareo.estadoQ1(),
                    "ENVIADO",
                    tareo.fechaEnvioQ1(),
                    reloj.ahora(),
                    tareo.usuarioEnvioQ1(),
                    command.usuarioId()
            );
        }
        Tareo guardado = tareoRepository.save(actualizado);

        notificador.enviar(
                rrhhEmail,
                "Tareo enviado - Q" + command.quincena(),
                "El tareo " + guardado.id() + " del area " + guardado.areaId()
                        + " fue culminado en la quincena " + command.quincena() + ".");

        return guardado;
    }

    private void validarCompletitud(Long tareoId, Long periodoId, int quincena) {
        List<PeriodoDia> dias = periodoRepository.findDiasByPeriodoId(periodoId).stream()
                .filter(d -> d.quincena() == quincena)
                .toList();
        List<TareoColaborador> colaboradores = tareoRepository.findColaboradoresByTareoId(tareoId);
        List<Asistencia> asistencias = tareoRepository.findAsistenciasByTareoIdAndQuincena(tareoId, quincena);

        Map<Long, Map<Long, Asistencia>> porColaboradorYDia = asistencias.stream()
                .collect(Collectors.groupingBy(
                        Asistencia::tareoColaboradorId,
                        Collectors.toMap(Asistencia::periodoDiaId, a -> a, (a, b) -> b)));

        for (TareoColaborador col : colaboradores) {
            Map<Long, Asistencia> porDia = porColaboradorYDia.getOrDefault(col.id(), Map.of());
            for (PeriodoDia dia : dias) {
                Asistencia asistencia = porDia.get(dia.id());
                if (asistencia == null || asistencia.categoriaCodigo() == null
                        || asistencia.categoriaCodigo().isBlank()) {
                    throw new IllegalArgumentException(
                            "Hay registros pendientes para " + col.nombresSnapshot());
                }
            }
        }
    }
}
