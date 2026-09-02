package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "turno", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TurnoEntity {
    @Id
    private String id;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false)
    private Boolean activo;
}
