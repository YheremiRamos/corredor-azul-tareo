package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.ctarequipa.tareo.application.port.out.ColaboradorRepository;
import pe.ctarequipa.tareo.domain.model.Colaborador;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.mapper.PersistenceMapper;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository.ColaboradorJpaRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ColaboradorJpaAdapter implements ColaboradorRepository {

    private final ColaboradorJpaRepository colaboradorJpaRepository;
    private final PersistenceMapper mapper;

    @Override
    public Optional<Colaborador> findById(Long id) {
        return colaboradorJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Colaborador> findAll() {
        return mapper.toColaboradorDomainList(colaboradorJpaRepository.findAll());
    }

    @Override
    public List<Colaborador> findByAreaId(String areaId) {
        return mapper.toColaboradorDomainList(colaboradorJpaRepository.findByAreaId(areaId));
    }

    @Override
    public List<Colaborador> findByAreaIdAndSubareaId(String areaId, String subareaId) {
        return mapper.toColaboradorDomainList(
                colaboradorJpaRepository.findByAreaIdAndSubareaId(areaId, subareaId));
    }

    @Override
    public long countActivos() {
        return colaboradorJpaRepository.countByActivoTrue();
    }
}
