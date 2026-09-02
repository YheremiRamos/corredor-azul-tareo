package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.port.in.ListarColaboradoresUseCase;
import pe.ctarequipa.tareo.application.port.out.ColaboradorRepository;
import pe.ctarequipa.tareo.domain.model.Colaborador;

import java.util.List;

public class ListarColaboradoresService implements ListarColaboradoresUseCase {

    private final ColaboradorRepository colaboradorRepository;

    public ListarColaboradoresService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    public List<Colaborador> listar(String areaId) {
        if (areaId == null || areaId.isBlank()) {
            return colaboradorRepository.findAll();
        }
        return colaboradorRepository.findByAreaId(areaId);
    }
}
