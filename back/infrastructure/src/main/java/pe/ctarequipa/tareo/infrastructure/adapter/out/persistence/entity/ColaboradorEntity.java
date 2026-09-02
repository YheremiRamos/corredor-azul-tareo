package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "colaborador", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 20)
    private String codigo;
    @Column(nullable = false, length = 20)
    private String dni;
    @Column(nullable = false, length = 200)
    private String nombres;
    @Column(name = "tipo_trabajador_id", nullable = false, length = 10)
    private String tipoTrabajadorId;
    @Column(name = "area_id", nullable = false, length = 10)
    private String areaId;
    @Column(name = "subarea_id", length = 20)
    private String subareaId;
    @Column(length = 100)
    private String cargo;
    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;
    @Column(name = "fecha_cese")
    private LocalDate fechaCese;
    @Column(nullable = false)
    private Boolean activo;
    @Column(name = "es_jefatura", nullable = false)
    private Boolean esJefatura;
}
