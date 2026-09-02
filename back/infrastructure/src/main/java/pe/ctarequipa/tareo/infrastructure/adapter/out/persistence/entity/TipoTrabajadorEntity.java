package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_trabajador", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoTrabajadorEntity {
    @Id
    private String id;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false)
    private Boolean activo;
}
