package pe.ctarequipa.tareo.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.ctarequipa.tareo.application.port.out.Reloj;
import pe.ctarequipa.tareo.application.port.out.TareoRepository;
import pe.ctarequipa.tareo.domain.model.Tareo;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.AuditoriaCambioEntity;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository.AuditoriaCambioJpaRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditoriaService {

    private final AuditoriaCambioJpaRepository auditoriaRepository;
    private final Reloj reloj;

    @Transactional
    public void registrar(
            Long usuarioId,
            String usuarioNombre,
            String entidad,
            String entidadId,
            String campo,
            String valorAnterior,
            String valorNuevo,
            String motivo) {
        AuditoriaCambioEntity registro = AuditoriaCambioEntity.builder()
                .usuarioId(usuarioId)
                .usuarioNombre(usuarioNombre)
                .fechaHora(reloj.ahora())
                .entidad(entidad)
                .entidadId(entidadId)
                .campo(campo)
                .valorAnterior(valorAnterior)
                .valorNuevo(valorNuevo)
                .motivo(motivo)
                .build();
        auditoriaRepository.save(registro);
        log.debug("Auditoria registrada: {} {} {}", entidad, entidadId, campo);
    }
}
