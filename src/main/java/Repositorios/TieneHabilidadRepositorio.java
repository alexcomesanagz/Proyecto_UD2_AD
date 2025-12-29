package Repositorios;

import org.hibernate.Session;

public class TieneHabilidadRepositorio {
    Session session;

    public TieneHabilidadRepositorio(Session session){
        this.session = session;
    }

}
