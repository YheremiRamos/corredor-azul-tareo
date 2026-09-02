package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.PeriodoEntity;

import java.util.Optional;

public interface PeriodoJpaRepository extends JpaRepository<PeriodoEntity, Long> {
    Optional<PeriodoEntity> findByAnioAndMes(int anio, int mes);
    long countByEstado(String estado);
}
