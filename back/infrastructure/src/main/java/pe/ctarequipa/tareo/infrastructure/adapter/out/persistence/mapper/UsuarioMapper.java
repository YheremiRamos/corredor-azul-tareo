package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.ctarequipa.tareo.application.model.Usuario;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.UsuarioEntity;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "passwordHash", source = "passwordHash")
    @Mapping(target = "areaIds", expression = "java(toAreaIds(entity))")
    @Mapping(target = "subareaIds", expression = "java(toSubareaIds(entity))")
    Usuario toDomain(UsuarioEntity entity);

    List<Usuario> toDomainList(List<UsuarioEntity> entities);

    default List<String> toAreaIds(UsuarioEntity entity) {
        if (entity.getAreas() == null) return List.of();
        return entity.getAreas().stream().map(a -> a.getId()).collect(Collectors.toList());
    }

    default List<String> toSubareaIds(UsuarioEntity entity) {
        if (entity.getSubareas() == null) return List.of();
        return entity.getSubareas().stream().map(s -> s.getId()).collect(Collectors.toList());
    }
}
