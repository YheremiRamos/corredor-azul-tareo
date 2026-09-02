package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.port.in.GuardarAsistenciasUseCase;
import pe.ctarequipa.tareo.application.port.in.command.AsistenciaItemCommand;
import pe.ctarequipa.tareo.application.port.in.command.GuardarAsistenciasCommand;
import pe.ctarequipa.tareo.application.port.out.TareoRepository;
import pe.ctarequipa.tareo.domain.exception.TareoBloqueadoException;
import pe.ctarequipa.tareo.domain.model.Asistencia;
import pe.ctarequipa.tareo.domain.model.Tareo;
import pe.ctarequipa.tareo.domain.service.ValidadorHorasExtras;

import java.util.ArrayList;
import java.util.List;

public class GuardarAsistenciasService implements GuardarAsistenciasUseCase {

    private final TareoRepository tareoRepository;

    public GuardarAsistenciasService(TareoRepository tareoRepository) {
        this.tareoRepository = tareoRepository;
    }

    @Override
    public void guardar(GuardarAsistenciasCommand command) {
        Tareo tareo = tareoRepository.findById(command.tareoId())
                .orElseThrow(() -> new IllegalArgumentException("Tareo no encontrado"));
        if (tareo.quincenaBloqueada(command.quincena())) {
            throw new TareoBloqueadoException("La quincena " + command.quincena() + " esta bloqueada");
        }

        List<Asistencia> asistencias = new ArrayList<>();
        for (AsistenciaItemCommand item : command.asistencias()) {
            ValidadorHorasExtras.validar(item.heTotal(), item.he25(), item.he30());
            asistencias.add(new Asistencia(
                    null,
                    item.tareoColaboradorId(),
                    item.periodoDiaId(),
                    item.categoriaCodigo(),
                    item.turnoId(),
                    item.bonificacionNocturna(),
                    item.heTotal(),
                    item.he25(),
                    item.he30(),
                    item.observacion()
            ));
        }
        tareoRepository.saveAsistencias(asistencias);
    }
}
