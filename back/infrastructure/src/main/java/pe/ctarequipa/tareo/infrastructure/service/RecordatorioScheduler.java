package pe.ctarequipa.tareo.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pe.ctarequipa.tareo.application.port.out.Notificador;
import pe.ctarequipa.tareo.application.port.out.TareoRepository;
import pe.ctarequipa.tareo.domain.model.Tareo;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecordatorioScheduler {

    private final TareoRepository tareoRepository;
    private final Notificador notificador;

    @Scheduled(cron = "${app.recordatorio.cron:0 0 8 * * MON}")
    public void enviarRecordatorios() {
        List<Tareo> pendientes = tareoRepository.findAll().stream()
                .filter(t -> "PENDIENTE".equals(t.estadoQ1()) || "EN_PROCESO".equals(t.estadoQ1())
                        || "PENDIENTE".equals(t.estadoQ2()) || "EN_PROCESO".equals(t.estadoQ2()))
                .toList();
        if (pendientes.isEmpty()) {
            log.debug("No hay tareos pendientes para recordatorio");
            return;
        }
        log.info("Enviando recordatorios para {} tareos pendientes", pendientes.size());
        notificador.enviar(
                "rrhh@ctarequipa.pe",
                "Recordatorio de Tareo",
                "Hay " + pendientes.size() + " tareos con quincenas pendientes de envio."
        );
    }
}
