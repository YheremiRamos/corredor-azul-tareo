package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.PeriodoDiaEntity;

import java.util.List;

public interface PeriodoDiaJpaRepository extends JpaRepository<PeriodoDiaEntity, Long> {
    List<PeriodoDiaEntity> findByPeriodo_IdOrderByOrden(Long periodoId);
}
