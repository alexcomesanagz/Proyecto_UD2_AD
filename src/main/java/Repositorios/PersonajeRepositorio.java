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

    public void eliminarPersonaje(String nombre) {
        Transaction trx = session.beginTransaction();
        session.remove(encontrarPorNombre(nombre));
        trx.commit();
    }
    public Personaje encontrarPorNombre(String nombre) {
        Query query = session.createQuery("SELECT p FROM Personaje p WHERE p.nombre=:nombre");
        query.setParameter(nombre, ":nombre");
        return (Personaje) query.getSingleResult();
    }
}
