package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.TareoEntity;

import java.util.List;
import java.util.Optional;

public interface TareoJpaRepository extends JpaRepository<TareoEntity, Long> {
    List<TareoEntity> findByPeriodoId(Long periodoId);

    @Query("""
            SELECT t FROM TareoEntity t
            WHERE t.periodoId = :periodoId AND t.areaId = :areaId
            AND ((:subareaId IS NULL AND t.subareaId IS NULL) OR t.subareaId = :subareaId)
            """)
    Optional<TareoEntity> findByPeriodoAreaSubarea(
            @Param("periodoId") Long periodoId,
            @Param("areaId") String areaId,
            @Param("subareaId") String subareaId);

    long countByEstadoQ1OrEstadoQ2(String estadoQ1, String estadoQ2);
}
