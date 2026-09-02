package pe.ctarequipa.tareo.application.service;

import pe.ctarequipa.tareo.application.model.DashboardStats;
import pe.ctarequipa.tareo.application.port.in.DashboardStatsUseCase;
import pe.ctarequipa.tareo.application.port.out.ColaboradorRepository;
import pe.ctarequipa.tareo.application.port.out.PeriodoRepository;
import pe.ctarequipa.tareo.application.port.out.TareoRepository;

public class DashboardStatsService implements DashboardStatsUseCase {

    private final ColaboradorRepository colaboradorRepository;
    private final PeriodoRepository periodoRepository;
    private final TareoRepository tareoRepository;

    public DashboardStatsService(
            ColaboradorRepository colaboradorRepository,
            PeriodoRepository periodoRepository,
            TareoRepository tareoRepository) {
        this.colaboradorRepository = colaboradorRepository;
        this.periodoRepository = periodoRepository;
        this.tareoRepository = tareoRepository;
    }

    @Override
    public DashboardStats obtener() {
        return new DashboardStats(
                colaboradorRepository.countActivos(),
                periodoRepository.countAbiertos(),
                tareoRepository.countPendientes(),
                0
        );
    }
}
