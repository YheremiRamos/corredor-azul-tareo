package pe.ctarequipa.tareo.application.port.out;

import pe.ctarequipa.tareo.domain.model.Periodo;
import pe.ctarequipa.tareo.domain.model.PeriodoDia;

import java.util.List;
import java.util.Optional;

public interface PeriodoRepository {
    Optional<Periodo> findById(Long id);
    Optional<Periodo> findByAnioMes(int anio, int mes);
    List<Periodo> findAll();
    Periodo save(Periodo periodo);
    List<PeriodoDia> findDiasByPeriodoId(Long periodoId);
    void saveDias(Long periodoId, List<PeriodoDia> dias);
    long countAbiertos();
}
