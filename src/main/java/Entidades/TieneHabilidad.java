package Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Personaje_Habilidad")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TieneHabilidad {

    @EmbeddedId
    private HabilidadId id;

    @ManyToOne
    @MapsId("idHabilidad")
    @JoinColumn(name = "id_habilidad")
    private Habilidad habilidad;

    @ManyToOne
    @MapsId("idPersonaje")
    @JoinColumn(name = "id_personaje")
    private Personaje personaje;

}
