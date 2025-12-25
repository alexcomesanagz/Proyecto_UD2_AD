package Repositorios;


import Entidades.Personaje;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


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

    public void eliminarPersonaje(int id) {
        Transaction trx = session.beginTransaction();
        session.remove(encontrarPorId(id));
        trx.commit();
    }
    public Personaje encontrarPorId(int id) {
        Query query = session.createQuery("SELECT p FROM Personaje p WHERE p.id=:id");
        query.setParameter(id, ":id");
        return (Personaje) query.getSingleResult();
    }
}
