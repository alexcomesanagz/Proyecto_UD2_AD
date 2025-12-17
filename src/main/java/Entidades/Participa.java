package Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "Participa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participa {

    @EmbeddedId
    private ParticipaId id;

    @ManyToOne
    @MapsId("idEvento")
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @ManyToOne
    @MapsId("idPersonaje")
    @JoinColumn(name = "id_personaje")
    private Personaje personaje;

    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "rol")
    private String rol;
}
