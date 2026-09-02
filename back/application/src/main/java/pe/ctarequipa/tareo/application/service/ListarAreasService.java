package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.port.in.ListarAreasUseCase;
import pe.ctarequipa.tareo.application.port.out.AreaRepository;
import pe.ctarequipa.tareo.domain.model.Area;

import java.util.List;

public class ListarAreasService implements ListarAreasUseCase {

    private final AreaRepository areaRepository;

    public ListarAreasService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public List<Area> listar() {
        return areaRepository.findAllActivas();
    }
}
