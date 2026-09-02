package pe.ctarequipa.tareo.application.port.out;

import pe.ctarequipa.tareo.domain.model.Colaborador;

import java.util.List;
import java.util.Optional;

public interface ColaboradorRepository {
    Optional<Colaborador> findById(Long id);
    List<Colaborador> findAll();
    List<Colaborador> findByAreaId(String areaId);
    List<Colaborador> findByAreaIdAndSubareaId(String areaId, String subareaId);
    long countActivos();
}
