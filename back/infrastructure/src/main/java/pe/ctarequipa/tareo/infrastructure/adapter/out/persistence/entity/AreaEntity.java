package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "area", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaEntity {
    @Id
    private String id;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false)
    private Boolean activo;
}
