package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    @Column(nullable = false, length = 150)
    private String nombre;
    @Column(length = 150)
    private String email;
    @Column(nullable = false)
    private Boolean activo;
    @Column(name = "rol_id", nullable = false, length = 20)
    private String rolId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_area",
            schema = "app",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id")
    )
    @Builder.Default
    private Set<AreaEntity> areas = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_subarea",
            schema = "app",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "subarea_id")
    )
    @Builder.Default
    private Set<SubareaEntity> subareas = new HashSet<>();
}
