package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.AsistenciaEntity;

import java.util.List;
import java.util.Optional;

public interface AsistenciaJpaRepository extends JpaRepository<AsistenciaEntity, Long> {

    @Query("""
            SELECT a FROM AsistenciaEntity a
            JOIN TareoColaboradorEntity tc ON a.tareoColaboradorId = tc.id
            JOIN PeriodoDiaEntity pd ON a.periodoDiaId = pd.id
            WHERE tc.tareoId = :tareoId AND pd.quincena = :quincena
            """)
    List<AsistenciaEntity> findByTareoIdAndQuincena(@Param("tareoId") Long tareoId, @Param("quincena") int quincena);

    List<AsistenciaEntity> findByTareoColaboradorIdIn(List<Long> tareoColaboradorIds);

    Optional<AsistenciaEntity> findByTareoColaboradorIdAndPeriodoDiaId(Long tareoColaboradorId, Long periodoDiaId);
}
