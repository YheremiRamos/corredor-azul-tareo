package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.ctarequipa.tareo.application.port.out.ConfiguracionRepository;
import pe.ctarequipa.tareo.domain.model.ConfiguracionTareo;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.mapper.PersistenceMapper;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository.ConfiguracionTareoJpaRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ConfiguracionJpaAdapter implements ConfiguracionRepository {

    private final ConfiguracionTareoJpaRepository configuracionTareoJpaRepository;
    private final PersistenceMapper mapper;

    @Override
    public Optional<ConfiguracionTareo> findActiva() {
        return configuracionTareoJpaRepository.findAll().stream()
                .findFirst()
                .map(mapper::toDomain);
    }
}
