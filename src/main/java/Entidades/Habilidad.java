package Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Habilidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String nombre;
    @NonNull
    private String descripcion;

    @ManyToMany(mappedBy = "listaHabilidades")
    private List<Personaje> listaPersonajes;
    public void addListaPersonajes(Personaje personaje){
        this.listaPersonajes.add(personaje);
    }
}
