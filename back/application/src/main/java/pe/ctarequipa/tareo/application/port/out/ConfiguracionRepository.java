package pe.ctarequipa.tareo.application.port.out;

import pe.ctarequipa.tareo.domain.model.ConfiguracionTareo;

import java.util.Optional;

public interface ConfiguracionRepository {
    Optional<ConfiguracionTareo> findActiva();
}
