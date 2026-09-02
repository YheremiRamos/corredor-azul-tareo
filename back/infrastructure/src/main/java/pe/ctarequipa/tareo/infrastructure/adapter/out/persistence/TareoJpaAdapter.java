package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.ctarequipa.tareo.application.port.out.TareoRepository;
import pe.ctarequipa.tareo.domain.model.Asistencia;
import pe.ctarequipa.tareo.domain.model.Tareo;
import pe.ctarequipa.tareo.domain.model.TareoColaborador;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.AsistenciaEntity;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.TareoColaboradorEntity;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.TareoEntity;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.mapper.PersistenceMapper;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository.AsistenciaJpaRepository;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository.TareoColaboradorJpaRepository;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository.TareoJpaRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TareoJpaAdapter implements TareoRepository {

    private final TareoJpaRepository tareoJpaRepository;
    private final TareoColaboradorJpaRepository tareoColaboradorJpaRepository;
    private final AsistenciaJpaRepository asistenciaJpaRepository;
    private final PersistenceMapper mapper;

    @Override
    public Optional<Tareo> findById(Long id) {
        return tareoJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Tareo> findAll() {
        return tareoJpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Tareo> findByPeriodoId(Long periodoId) {
        return tareoJpaRepository.findByPeriodoId(periodoId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Tareo> findByPeriodoAreaSubarea(Long periodoId, String areaId, String subareaId) {
        return tareoJpaRepository.findByPeriodoAreaSubarea(periodoId, areaId, subareaId)
                .map(mapper::toDomain);
    }

    @Override
    public Tareo save(Tareo tareo) {
        TareoEntity entity = mapper.toEntity(tareo);
        return mapper.toDomain(tareoJpaRepository.save(entity));
    }

    @Override
    public List<TareoColaborador> findColaboradoresByTareoId(Long tareoId) {
        return mapper.toTareoColaboradorDomainList(tareoColaboradorJpaRepository.findByTareoId(tareoId));
    }

    @Override
    public void saveColaboradores(List<TareoColaborador> colaboradores) {
        List<TareoColaboradorEntity> entities = colaboradores.stream()
                .map(mapper::toEntity)
                .toList();
        tareoColaboradorJpaRepository.saveAll(entities);
    }

    @Override
    public List<Asistencia> findAsistenciasByTareoIdAndQuincena(Long tareoId, int quincena) {
        return asistenciaJpaRepository.findByTareoIdAndQuincena(tareoId, quincena).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void saveAsistencias(List<Asistencia> asistencias) {
        for (Asistencia a : asistencias) {
            AsistenciaEntity entity = mapper.toEntity(a);
            asistenciaJpaRepository.save(entity);
        }
    }

    @Override
    public long countPendientes() {
        return tareoJpaRepository.countByEstadoQ1OrEstadoQ2("PENDIENTE", "PENDIENTE");
    }
}
