package Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Habilidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "habilidad")
    private List<TieneHabilidad> habilidades;
}
