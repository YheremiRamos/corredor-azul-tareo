package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.ctarequipa.tareo.domain.model.*;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersistenceMapper {

    Area toDomain(AreaEntity entity);
    List<Area> toAreaDomainList(List<AreaEntity> entities);

    Subarea toDomain(SubareaEntity entity);

    @Mapping(target = "dias", ignore = true)
    Periodo toDomain(PeriodoEntity entity);

    PeriodoDia toDomain(PeriodoDiaEntity entity);
    List<PeriodoDia> toPeriodoDiaDomainList(List<PeriodoDiaEntity> entities);

    default PeriodoDiaEntity toEntity(PeriodoDia domain, PeriodoEntity periodo) {
        return PeriodoDiaEntity.builder()
                .id(domain.id())
                .periodo(periodo)
                .fecha(domain.fecha())
                .orden(domain.orden())
                .quincena(domain.quincena())
                .diaSemana(domain.diaSemana())
                .build();
    }

    @Mapping(target = "dias", ignore = true)
    PeriodoEntity toEntity(Periodo domain);

    Tareo toDomain(TareoEntity entity);
    TareoEntity toEntity(Tareo domain);

    TareoColaborador toDomain(TareoColaboradorEntity entity);
    List<TareoColaborador> toTareoColaboradorDomainList(List<TareoColaboradorEntity> entities);
    TareoColaboradorEntity toEntity(TareoColaborador domain);

    Asistencia toDomain(AsistenciaEntity entity);
    AsistenciaEntity toEntity(Asistencia domain);

    Colaborador toDomain(ColaboradorEntity entity);
    List<Colaborador> toColaboradorDomainList(List<ColaboradorEntity> entities);

    @Mapping(target = "diaCorteQuincena1", source = "diaCorteQ1")
    @Mapping(target = "diaInicioQuincena2", source = "diaInicioQ2")
    ConfiguracionTareo toDomain(ConfiguracionTareoEntity entity);
}
