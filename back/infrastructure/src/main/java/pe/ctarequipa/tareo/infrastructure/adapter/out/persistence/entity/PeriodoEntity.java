package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "periodo", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer anio;
    @Column(nullable = false)
    private Integer mes;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;
    @Column(nullable = false, length = 20)
    private String estado;

    @OneToMany(mappedBy = "periodo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PeriodoDiaEntity> dias = new ArrayList<>();
}
