package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "configuracion_tareo", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionTareoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dia_inicio_mes", nullable = false)
    private Integer diaInicioMes;
    @Column(name = "dia_fin_mes", nullable = false)
    private Integer diaFinMes;
    @Column(name = "dia_corte_q1", nullable = false)
    private Integer diaCorteQ1;
    @Column(name = "dia_inicio_q2", nullable = false)
    private Integer diaInicioQ2;
}
