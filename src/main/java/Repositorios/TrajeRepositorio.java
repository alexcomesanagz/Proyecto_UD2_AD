package Repositorios;

import org.hibernate.Session;

public class TrajeRepositorio {
    Session session;

    public TrajeRepositorio(Session session){
        this.session = session;
    }
}
