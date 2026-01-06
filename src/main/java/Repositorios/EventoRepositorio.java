package Repositorios;


import Entidades.Evento;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class EventoRepositorio {
    Session session;

    public EventoRepositorio(Session session){
        this.session = session;
    }

    public Evento encontrarPorNombre(String nombreEvento) {
        try{
            Query query = session.createQuery("SELECT e FROM Evento e WHERE e.nombre=:nombre");
            query.setParameter("nombre", nombreEvento);
            return (Evento) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    //funciones
}
