package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.ctarequipa.tareo.application.port.out.PeriodoRepository;
import pe.ctarequipa.tareo.domain.model.Periodo;
import pe.ctarequipa.tareo.domain.model.PeriodoDia;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.PeriodoDiaEntity;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.PeriodoEntity;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.mapper.PersistenceMapper;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository.PeriodoDiaJpaRepository;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository.PeriodoJpaRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PeriodoJpaAdapter implements PeriodoRepository {

    private final PeriodoJpaRepository periodoJpaRepository;
    private final PeriodoDiaJpaRepository periodoDiaJpaRepository;
    private final PersistenceMapper mapper;

    @Override
    public Optional<Periodo> findById(Long id) {
        return periodoJpaRepository.findById(id).map(this::toDomainWithDias);
    }

    @Override
    public Optional<Periodo> findByAnioMes(int anio, int mes) {
        return periodoJpaRepository.findByAnioAndMes(anio, mes).map(this::toDomainWithDias);
    }

    @Override
    public List<Periodo> findAll() {
        return periodoJpaRepository.findAll().stream().map(this::toDomainWithDias).toList();
    }

    @Override
    public Periodo save(Periodo periodo) {
        PeriodoEntity entity = mapper.toEntity(periodo);
        PeriodoEntity saved = periodoJpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<PeriodoDia> findDiasByPeriodoId(Long periodoId) {
        return mapper.toPeriodoDiaDomainList(periodoDiaJpaRepository.findByPeriodo_IdOrderByOrden(periodoId));
    }

    @Override
    public void saveDias(Long periodoId, List<PeriodoDia> dias) {
        PeriodoEntity periodo = periodoJpaRepository.findById(periodoId)
                .orElseThrow(() -> new IllegalArgumentException("Periodo no encontrado"));
        List<PeriodoDiaEntity> entities = dias.stream()
                .map(d -> mapper.toEntity(d, periodo))
                .toList();
        periodoDiaJpaRepository.saveAll(entities);
    }

    @Override
    public long countAbiertos() {
        return periodoJpaRepository.countByEstado("ACTIVO");
    }

    private Periodo toDomainWithDias(PeriodoEntity entity) {
        List<PeriodoDia> dias = mapper.toPeriodoDiaDomainList(
                periodoDiaJpaRepository.findByPeriodo_IdOrderByOrden(entity.getId()));
        Periodo base = mapper.toDomain(entity);
        return new Periodo(
                base.id(), base.anio(), base.mes(), base.nombre(),
                base.fechaInicio(), base.fechaFin(), base.estado(), dias);
    }
}
