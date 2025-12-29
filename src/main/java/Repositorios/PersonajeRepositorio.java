package Repositorios;


import Entidades.Personaje;
import jakarta.persistence.NoResultException;
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
        System.out.println("Personaje creado con éxito.");
    }

    public void eliminarPersonaje(int id) {
        Transaction trx = session.beginTransaction();
        Personaje personaje = encontrarPorId(id);
        if(personaje != null){
            session.remove(personaje);
            trx.commit();
            System.out.println("Personaje eliminado con éxito.");
        }else {
            trx.rollback();
            System.out.println("Personaje no econtrado");
        }

    }

    public Personaje encontrarPorId(int id) {
        try{
            Query query = session.createQuery("SELECT p FROM Personaje p WHERE p.id=:id");
            query.setParameter(id, ":id");
            return (Personaje) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public void modificarPersonaje(int id, String nombre) {
        Transaction trx = session.beginTransaction();
        Personaje personaje = encontrarPorId(id);
        if(personaje!= null){
            personaje.setNombre(nombre);
            trx.commit();
            System.out.println("Personaje modificado con éxito.");
        }else{
            trx.rollback();
            System.out.println("Personaje no encontrado.");
        }
    }
}
