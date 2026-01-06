package Entidades;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class ParticipaId implements Serializable {
    private int idEvento;
    private int idPersonaje;
}
