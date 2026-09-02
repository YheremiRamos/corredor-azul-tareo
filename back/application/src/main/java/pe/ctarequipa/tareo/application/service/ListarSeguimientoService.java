package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.model.SeguimientoItem;
import pe.ctarequipa.tareo.application.port.in.ListarSeguimientoUseCase;
import pe.ctarequipa.tareo.application.port.out.TareoRepository;
import pe.ctarequipa.tareo.domain.model.Tareo;

import java.util.List;

public class ListarSeguimientoService implements ListarSeguimientoUseCase {

    private final TareoRepository tareoRepository;

    public ListarSeguimientoService(TareoRepository tareoRepository) {
        this.tareoRepository = tareoRepository;
    }

    @Override
    public List<SeguimientoItem> listar() {
        return tareoRepository.findAll().stream()
                .map(this::toItem)
                .toList();
    }

    private SeguimientoItem toItem(Tareo t) {
        String desc = "Tareo " + t.id() + " - Area " + t.areaId()
                + " Q1:" + t.estadoQ1() + " Q2:" + t.estadoQ2();
        return new SeguimientoItem(String.valueOf(t.id()), desc);
    }
}
