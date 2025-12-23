package Repositorios;


import org.hibernate.Session;

public class PersonajeRepositorio {
    Session session;

    public PersonajeRepositorio(Session session){
        this.session = session;
    }

}
