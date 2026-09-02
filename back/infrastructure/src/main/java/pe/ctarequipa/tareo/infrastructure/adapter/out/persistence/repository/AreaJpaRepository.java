package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.AreaEntity;

import java.util.List;

public interface AreaJpaRepository extends JpaRepository<AreaEntity, String> {
    List<AreaEntity> findByActivoTrue();
}
