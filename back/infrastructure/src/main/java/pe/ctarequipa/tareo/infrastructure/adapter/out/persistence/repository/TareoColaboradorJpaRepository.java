package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.TareoColaboradorEntity;

import java.util.List;

public interface TareoColaboradorJpaRepository extends JpaRepository<TareoColaboradorEntity, Long> {
    List<TareoColaboradorEntity> findByTareoId(Long tareoId);
}
