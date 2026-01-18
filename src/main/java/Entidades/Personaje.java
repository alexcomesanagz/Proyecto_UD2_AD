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
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    private String nombre;
    @NonNull
    private String alias;


    @OneToOne
    @JoinColumn(name = "id_traje", unique = true)
    private Traje traje;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "Personaje_Habilidad",
            joinColumns = @JoinColumn(name = "id_personaje"),
            inverseJoinColumns = @JoinColumn(name = "id_habilidad")
    )
    private List<Habilidad> listaHabilidades;

    @OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participa> participantes;

    public void setTrajeBidireccional(Traje traje){
        this.traje = traje;
        traje.setPersonaje(this);
    }

    public void setHabilidadBidireccional(Habilidad habilidad){
        listaHabilidades.add(habilidad);
        habilidad.addListaPersonajes(this);
    }

    @Override
    public String toString(){
        return "ID: " + id +
                "\nNombre: " + nombre +
                "\nAlias: " + alias +
                "\n------------------------------------------" +
                "\nTraje: " + traje +
                "\n------------------------------------------" +
                "\nHabilidades: " + listaHabilidades +
                "\n------------------------------------------" +
                "\nEventos: " + participantes +
                "\n------------------------------------------";
    }
}
