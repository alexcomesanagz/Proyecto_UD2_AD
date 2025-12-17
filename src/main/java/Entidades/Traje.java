package Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Traje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Traje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String especificacion;

    @OneToOne(mappedBy = "traje")
    private Personaje personaje;

}
