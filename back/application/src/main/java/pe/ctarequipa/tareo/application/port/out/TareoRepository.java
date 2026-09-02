package pe.ctarequipa.tareo.application.port.out;

import pe.ctarequipa.tareo.domain.model.Asistencia;
import pe.ctarequipa.tareo.domain.model.Tareo;
import pe.ctarequipa.tareo.domain.model.TareoColaborador;

import java.util.List;
import java.util.Optional;

public interface TareoRepository {
    Optional<Tareo> findById(Long id);
    List<Tareo> findAll();
    List<Tareo> findByPeriodoId(Long periodoId);
    Optional<Tareo> findByPeriodoAreaSubarea(Long periodoId, String areaId, String subareaId);
    Tareo save(Tareo tareo);
    List<TareoColaborador> findColaboradoresByTareoId(Long tareoId);
    void saveColaboradores(List<TareoColaborador> colaboradores);
    List<Asistencia> findAsistenciasByTareoIdAndQuincena(Long tareoId, int quincena);
    void saveAsistencias(List<Asistencia> asistencias);
    long countPendientes();
}
