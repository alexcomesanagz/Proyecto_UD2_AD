package Entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Traje")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Traje {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String especificacion;

    @OneToOne(mappedBy = "traje")
    private Personaje personaje;

    @Override
    public String toString(){
        return "\nID: " + id +
                "\nEspecificación: " + especificacion;
    }
}
