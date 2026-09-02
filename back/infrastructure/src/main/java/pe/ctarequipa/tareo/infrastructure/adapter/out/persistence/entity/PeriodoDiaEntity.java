package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "periodo_dia", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoDiaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "periodo_id", nullable = false)
    private PeriodoEntity periodo;
    @Column(nullable = false)
    private LocalDate fecha;
    @Column(nullable = false)
    private Integer orden;
    @Column(nullable = false)
    private Integer quincena;
    @Column(name = "dia_semana", nullable = false, length = 2)
    private String diaSemana;
}
