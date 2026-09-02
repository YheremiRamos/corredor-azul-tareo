package pe.ctarequipa.tareo.infrastructure.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.ctarequipa.tareo.application.port.in.DashboardStatsUseCase;
import pe.ctarequipa.tareo.application.model.DashboardStats;
import pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto.DashboardStatsResponse;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardStatsUseCase dashboardStatsUseCase;

    @GetMapping("/stats")
    public DashboardStatsResponse stats() {
        DashboardStats stats = dashboardStatsUseCase.obtener();
        return new DashboardStatsResponse(
                stats.colaboradoresActivos(),
                stats.periodosAbiertos(),
                stats.tareosPendientes(),
                stats.asistenciasHoy()
        );
    }
}
