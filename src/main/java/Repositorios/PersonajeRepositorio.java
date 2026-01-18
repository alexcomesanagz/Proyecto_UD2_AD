package Repositorios;


import Entidades.Personaje;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class PersonajeRepositorio {
    private Session session;

    public PersonajeRepositorio(Session session){
        this.session = session;
    }

    public void crearPersonaje(Personaje personaje){
        Transaction trx = session.beginTransaction();
        Query query = session.createQuery("SELECT MAX(id) FROM Personaje p");
        int id = 1+ (Integer) query.getSingleResult();
        personaje.setId(id);
        session.persist(personaje);
        trx.commit();
        System.out.println("Personaje creado con éxito.");
    }

    public void eliminarPersonaje(int id) {
        Transaction trx = session.beginTransaction();
        Personaje personaje = encontrarPorId(id);
        if(personaje != null){

            if(personaje.getTraje() != null){
                personaje.getTraje().setPersonaje(null);
            }

            session.remove(personaje);
            trx.commit();
            System.out.println("Personaje eliminado con éxito.");
        }else {
            trx.rollback();
            System.out.println("Personaje no econtrado");
        }

    }

    public Personaje encontrarPorNombre(String nombre) {
        try{
            Query query = session.createQuery("SELECT p FROM Personaje p WHERE p.nombre=:nombre");
            query.setParameter("nombre", nombre);
            return (Personaje) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public Personaje encontrarPorId(int id) {
        try{
            Query query = session.createQuery("SELECT p FROM Personaje p WHERE p.id=:id");
            query.setParameter("id", id);
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

    public void guardarCambios(Personaje p) {
        Transaction trx = null;
        try{
            trx = session.beginTransaction();
            session.merge(p);
            trx.commit();
        }catch (Exception e){
            if(trx != null) trx.rollback();
            throw e;
        }
    }

    public void mostrarIdsYNombre() {
        Query query = session.createQuery("SELECT p FROM Personaje p");
        List<Personaje> listaPersonajes = query.getResultList();
        if (listaPersonajes.isEmpty()) {
            System.out.println("No existen personajes.");
            return;
        }

        System.out.println("--- IDs de personajes: ---");
        for (Personaje personaje : listaPersonajes) {
            System.out.println("ID: " + personaje.getId() + " | Nombre: " + personaje.getNombre());
        }
        System.out.println("---------------------------");
    }
}
