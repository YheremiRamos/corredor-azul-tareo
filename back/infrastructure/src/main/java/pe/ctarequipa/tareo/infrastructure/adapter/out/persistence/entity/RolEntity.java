package pe.ctarequipa.tareo.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol", schema = "app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolEntity {
    @Id
    private String id;
    @Column(nullable = false, length = 50)
    private String nombre;
}
