package Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Personaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    private String nombre;
    @NonNull
    private String alias;


    @OneToOne
    @JoinColumn(name = "id_traje", unique = true)
    private Traje traje;

    @OneToMany(mappedBy = "personaje")
    private List<TieneHabilidad> habilidades;

    @OneToMany(mappedBy = "personaje")
    private List<Participa> participantes;

    public void setTrajeBidireccional(Traje traje){
        this.traje = traje;
        traje.setPersonaje(this);
    }
}
