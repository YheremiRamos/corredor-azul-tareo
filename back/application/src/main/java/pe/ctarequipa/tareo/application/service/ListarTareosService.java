package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.port.in.ListarTareosUseCase;
import pe.ctarequipa.tareo.application.port.out.TareoRepository;
import pe.ctarequipa.tareo.domain.model.Tareo;

import java.util.List;

public class ListarTareosService implements ListarTareosUseCase {

    private final TareoRepository tareoRepository;

    public ListarTareosService(TareoRepository tareoRepository) {
        this.tareoRepository = tareoRepository;
    }

    @Override
    public List<Tareo> listar() {
        return tareoRepository.findAll();
    }
}
