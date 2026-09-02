package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subarea", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubareaEntity {
    @Id
    private String id;
    @Column(name = "area_id", nullable = false, length = 10)
    private String areaId;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false)
    private Boolean activo;
}
