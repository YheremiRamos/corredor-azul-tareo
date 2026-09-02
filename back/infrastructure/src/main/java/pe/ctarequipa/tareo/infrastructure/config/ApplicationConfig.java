package pe.ctarequipa.tareo.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.ctarequipa.tareo.application.port.in.*;
import pe.ctarequipa.tareo.application.port.out.*;
import pe.ctarequipa.tareo.application.service.*;

@Configuration
public class ApplicationConfig {

    @Bean
    public AuthUseCase authUseCase(AuthProvider authProvider, TokenGenerator tokenGenerator) {
        return new AuthService(authProvider, tokenGenerator);
    }

    @Bean
    public CrearPeriodoUseCase crearPeriodoUseCase(
            PeriodoRepository periodoRepository, ConfiguracionRepository configuracionRepository) {
        return new CrearPeriodoService(periodoRepository, configuracionRepository);
    }

    @Bean
    public HabilitarTareoUseCase habilitarTareoUseCase(
            TareoRepository tareoRepository,
            PeriodoRepository periodoRepository,
            ColaboradorRepository colaboradorRepository) {
        return new HabilitarTareoService(tareoRepository, periodoRepository, colaboradorRepository);
    }

    @Bean
    public GuardarAsistenciasUseCase guardarAsistenciasUseCase(TareoRepository tareoRepository) {
        return new GuardarAsistenciasService(tareoRepository);
    }

    @Bean
    public CulminarQuincenaUseCase culminarQuincenaUseCase(
            TareoRepository tareoRepository,
            PeriodoRepository periodoRepository,
            Reloj reloj,
            Notificador notificador,
            @Value("${app.notificaciones.rrhh-email:rrhh@ctarequipa.pe}") String rrhhEmail) {
        return new CulminarQuincenaService(tareoRepository, periodoRepository, reloj, notificador, rrhhEmail);
    }

    @Bean
    public ConsultarMatrizUseCase consultarMatrizUseCase(
            TareoRepository tareoRepository, PeriodoRepository periodoRepository) {
        return new ConsultarMatrizService(tareoRepository, periodoRepository);
    }

    @Bean
    public ListarPeriodosUseCase listarPeriodosUseCase(PeriodoRepository periodoRepository) {
        return new ListarPeriodosService(periodoRepository);
    }

    @Bean
    public ListarAreasUseCase listarAreasUseCase(AreaRepository areaRepository) {
        return new ListarAreasService(areaRepository);
    }

    @Bean
    public ListarColaboradoresUseCase listarColaboradoresUseCase(ColaboradorRepository colaboradorRepository) {
        return new ListarColaboradoresService(colaboradorRepository);
    }

    @Bean
    public ListarTareosUseCase listarTareosUseCase(TareoRepository tareoRepository) {
        return new ListarTareosService(tareoRepository);
    }

    @Bean
    public ListarUsuariosUseCase listarUsuariosUseCase(UsuarioRepository usuarioRepository) {
        return new ListarUsuariosService(usuarioRepository);
    }

    @Bean
    public DashboardStatsUseCase dashboardStatsUseCase(
            ColaboradorRepository colaboradorRepository,
            PeriodoRepository periodoRepository,
            TareoRepository tareoRepository) {
        return new DashboardStatsService(colaboradorRepository, periodoRepository, tareoRepository);
    }

    @Bean
    public ListarSeguimientoUseCase listarSeguimientoUseCase(TareoRepository tareoRepository) {
        return new ListarSeguimientoService(tareoRepository);
    }

    @Bean
    public ListarReportesUseCase listarReportesUseCase() {
        return new ListarReportesService();
    }
}
