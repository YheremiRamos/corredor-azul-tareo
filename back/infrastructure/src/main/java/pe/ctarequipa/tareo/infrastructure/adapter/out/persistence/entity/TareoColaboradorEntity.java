package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tareo_colaborador", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TareoColaboradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tareo_id", nullable = false)
    private Long tareoId;
    @Column(name = "colaborador_id", nullable = false)
    private Long colaboradorId;
    @Column(name = "codigo_snapshot", nullable = false, length = 20)
    private String codigoSnapshot;
    @Column(name = "dni_snapshot", nullable = false, length = 20)
    private String dniSnapshot;
    @Column(name = "nombres_snapshot", nullable = false, length = 200)
    private String nombresSnapshot;
    @Column(name = "tipo_snapshot", nullable = false, length = 10)
    private String tipoSnapshot;
    @Column(name = "area_snapshot", nullable = false, length = 10)
    private String areaSnapshot;
    @Column(name = "subarea_snapshot", length = 20)
    private String subareaSnapshot;
    @Column(name = "cargo_snapshot", length = 100)
    private String cargoSnapshot;
}
