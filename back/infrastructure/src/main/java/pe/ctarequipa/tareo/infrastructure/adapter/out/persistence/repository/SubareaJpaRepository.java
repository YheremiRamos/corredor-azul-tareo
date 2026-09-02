package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.SubareaEntity;

import java.util.List;

public interface SubareaJpaRepository extends JpaRepository<SubareaEntity, String> {
    List<SubareaEntity> findByAreaId(String areaId);
}
