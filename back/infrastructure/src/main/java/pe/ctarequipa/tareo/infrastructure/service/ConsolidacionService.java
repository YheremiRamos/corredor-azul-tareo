package pe.ctarequipa.tareo.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.ctarequipa.tareo.application.port.out.Reloj;
import pe.ctarequipa.tareo.application.port.out.TareoRepository;
import pe.ctarequipa.tareo.domain.model.Tareo;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsolidacionService {

    private final TareoRepository tareoRepository;
    private final Reloj reloj;
    private final AuditoriaService auditoriaService;

    @Transactional
    public Tareo consolidarQuincena(Long tareoId, int quincena, Long usuarioId, String usuarioNombre) {
        Tareo tareo = tareoRepository.findById(tareoId)
                .orElseThrow(() -> new IllegalArgumentException("Tareo no encontrado"));

        String estadoActual = quincena == 1 ? tareo.estadoQ1() : tareo.estadoQ2();
        if (!"ENVIADO".equals(estadoActual)) {
            throw new IllegalArgumentException("Solo se puede consolidar quincenas enviadas");
        }

        Tareo consolidado;
        if (quincena == 1) {
            consolidado = new Tareo(
                    tareo.id(), tareo.periodoId(), tareo.areaId(), tareo.subareaId(),
                    tareo.habilitado(), "CONSOLIDADO", tareo.estadoQ2(),
                    tareo.fechaEnvioQ1(), tareo.fechaEnvioQ2(),
                    tareo.usuarioEnvioQ1(), tareo.usuarioEnvioQ2());
        } else {
            consolidado = new Tareo(
                    tareo.id(), tareo.periodoId(), tareo.areaId(), tareo.subareaId(),
                    tareo.habilitado(), tareo.estadoQ1(), "CONSOLIDADO",
                    tareo.fechaEnvioQ1(), tareo.fechaEnvioQ2(),
                    tareo.usuarioEnvioQ1(), tareo.usuarioEnvioQ2());
        }

        auditoriaService.registrar(
                usuarioId, usuarioNombre, "TAREO", String.valueOf(tareoId),
                "estadoQ" + quincena, estadoActual, "CONSOLIDADO", "Consolidacion RRHH");

        log.info("Tareo {} quincena {} consolidado", tareoId, quincena);
        return tareoRepository.save(consolidado);
    }
}
