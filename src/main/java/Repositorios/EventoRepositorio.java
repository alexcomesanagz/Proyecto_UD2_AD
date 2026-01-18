package Repositorios;


import Entidades.Evento;
import Entidades.Personaje;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

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

    public void mostrarIdsYNombre() {
        Query query = session.createQuery("SELECT e FROM Evento e");
        List<Evento> listaEventos = query.getResultList();
        if (listaEventos.isEmpty()) {
            System.out.println("No existen eventos.");
            return;
        }

        System.out.println("--- IDs de eventos: ---");
        for (Evento evento : listaEventos) {
            System.out.println("ID: " + evento.getId() + " | Nombre: " + evento.getNombre());
        }
        System.out.println("---------------------------");
    }

}
