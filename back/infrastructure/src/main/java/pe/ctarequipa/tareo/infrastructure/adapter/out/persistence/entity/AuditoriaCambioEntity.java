package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria_cambio", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaCambioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
    @Column(name = "usuario_nombre", nullable = false, length = 150)
    private String usuarioNombre;
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;
    @Column(nullable = false, length = 50)
    private String entidad;
    @Column(name = "entidad_id", nullable = false, length = 50)
    private String entidadId;
    @Column(nullable = false, length = 50)
    private String campo;
    @Column(name = "valor_anterior", length = 500)
    private String valorAnterior;
    @Column(name = "valor_nuevo", length = 500)
    private String valorNuevo;
    @Column(columnDefinition = "TEXT")
    private String motivo;
}
