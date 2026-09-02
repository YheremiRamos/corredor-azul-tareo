package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.ColaboradorEntity;

import java.util.List;

public interface ColaboradorJpaRepository extends JpaRepository<ColaboradorEntity, Long> {
    List<ColaboradorEntity> findByAreaId(String areaId);
    List<ColaboradorEntity> findByAreaIdAndSubareaId(String areaId, String subareaId);
    long countByActivoTrue();
}
