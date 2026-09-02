package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "asistencia", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tareo_colaborador_id", nullable = false)
    private Long tareoColaboradorId;
    @Column(name = "periodo_dia_id", nullable = false)
    private Long periodoDiaId;
    @Column(name = "categoria_codigo", length = 5)
    private String categoriaCodigo;
    @Column(name = "turno_id", length = 20)
    private String turnoId;
    @Column(name = "bonificacion_nocturna", nullable = false)
    private Boolean bonificacionNocturna;
    @Column(name = "he_total")
    private BigDecimal heTotal;
    @Column(name = "he_25")
    private BigDecimal he25;
    @Column(name = "he_30")
    private BigDecimal he30;
    @Column(columnDefinition = "TEXT")
    private String observacion;
}
