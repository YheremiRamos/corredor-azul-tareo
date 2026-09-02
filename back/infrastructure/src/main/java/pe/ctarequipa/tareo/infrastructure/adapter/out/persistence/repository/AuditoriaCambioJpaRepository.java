package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.AuditoriaCambioEntity;

public interface AuditoriaCambioJpaRepository extends JpaRepository<AuditoriaCambioEntity, Long> {}
