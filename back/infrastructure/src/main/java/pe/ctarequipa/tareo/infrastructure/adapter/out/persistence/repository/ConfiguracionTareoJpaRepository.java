package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.ConfiguracionTareoEntity;

public interface ConfiguracionTareoJpaRepository extends JpaRepository<ConfiguracionTareoEntity, Long> {}
