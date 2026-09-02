package pe.ctarequipa.tareo.application.port.out;

import pe.ctarequipa.tareo.domain.model.Area;
import pe.ctarequipa.tareo.domain.model.Subarea;

import java.util.List;
import java.util.Optional;

public interface AreaRepository {
    List<Area> findAllActivas();
    Optional<Area> findById(String id);
    List<Subarea> findSubareasByAreaId(String areaId);
}
