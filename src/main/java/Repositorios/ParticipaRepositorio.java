package Repositorios;


import org.hibernate.Session;

public class ParticipaRepositorio {
    Session session;

    public ParticipaRepositorio(Session session){
        this.session = session;
    }
}
