package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria_asistencia", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaAsistenciaEntity {
    @Id
    @Column(length = 5)
    private String codigo;
    @Column(nullable = false, length = 100)
    private String descripcion;
    @Column(nullable = false)
    private Boolean activo;
}
