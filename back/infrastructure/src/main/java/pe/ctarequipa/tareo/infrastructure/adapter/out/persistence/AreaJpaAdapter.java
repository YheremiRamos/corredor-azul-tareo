package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.ctarequipa.tareo.application.port.out.AreaRepository;
import pe.ctarequipa.tareo.domain.model.Area;
import pe.ctarequipa.tareo.domain.model.Subarea;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.mapper.PersistenceMapper;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository.AreaJpaRepository;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository.SubareaJpaRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AreaJpaAdapter implements AreaRepository {

    private final AreaJpaRepository areaJpaRepository;
    private final SubareaJpaRepository subareaJpaRepository;
    private final PersistenceMapper mapper;

    @Override
    public List<Area> findAllActivas() {
        return mapper.toAreaDomainList(areaJpaRepository.findByActivoTrue());
    }

    @Override
    public Optional<Area> findById(String id) {
        return areaJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Subarea> findSubareasByAreaId(String areaId) {
        return subareaJpaRepository.findByAreaId(areaId).stream().map(mapper::toDomain).toList();
    }
}
