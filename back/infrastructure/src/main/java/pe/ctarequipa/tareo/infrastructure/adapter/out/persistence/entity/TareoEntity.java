package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tareo", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TareoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "periodo_id", nullable = false)
    private Long periodoId;
    @Column(name = "area_id", nullable = false, length = 10)
    private String areaId;
    @Column(name = "subarea_id", length = 20)
    private String subareaId;
    @Column(nullable = false)
    private Boolean habilitado;
    @Column(name = "estado_q1", nullable = false, length = 20)
    private String estadoQ1;
    @Column(name = "estado_q2", nullable = false, length = 20)
    private String estadoQ2;
    @Column(name = "fecha_envio_q1")
    private LocalDateTime fechaEnvioQ1;
    @Column(name = "fecha_envio_q2")
    private LocalDateTime fechaEnvioQ2;
    @Column(name = "usuario_envio_q1", length = 50)
    private String usuarioEnvioQ1;
    @Column(name = "usuario_envio_q2", length = 50)
    private String usuarioEnvioQ2;
}
