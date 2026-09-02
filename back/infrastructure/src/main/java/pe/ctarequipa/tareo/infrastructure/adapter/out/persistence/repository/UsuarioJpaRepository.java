package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

    @Query("SELECT u FROM UsuarioEntity u LEFT JOIN FETCH u.areas LEFT JOIN FETCH u.subareas WHERE u.email = :value OR u.username = :value")
    Optional<UsuarioEntity> findByEmailOrUsername(@Param("value") String value);

    @Query("SELECT DISTINCT u FROM UsuarioEntity u LEFT JOIN FETCH u.areas LEFT JOIN FETCH u.subareas")
    List<UsuarioEntity> findAllWithAreas();
}
