package Repositorios;

import Entidades.Habilidad;
import org.hibernate.Session;

public class HabilidadRepositorio {
    Session session;

    public HabilidadRepositorio(Session session){
        this.session = session;
    }


    public void crearHabilidad(String nombre, String descripcion) {
        
    }

    public void eliminarHabilidad(String nombre) {
    }

    public void modificarPersonaje(String nombreAntiguo, String nombreNuevo) {
    }
}
