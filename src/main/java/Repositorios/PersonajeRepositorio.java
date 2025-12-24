package Repositorios;


import Entidades.Personaje;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PersonajeRepositorio {
    private Session session;

    public PersonajeRepositorio(Session session){
        this.session = session;
    }

    public void crearPersonaje(Personaje personaje){
        Transaction trx = session.beginTransaction();

        session.persist(personaje);

        trx.commit();
    }
}
