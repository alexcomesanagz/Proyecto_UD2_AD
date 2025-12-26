package Repositorios;

import org.hibernate.Session;

public class TieneHabilidadRepositorio {
    Session session;

    public TieneHabilidadRepositorio(Session session){
        this.session = session;
    }

    public void modificarPersonaje(String nombre, String habilidad) {
    }
}
