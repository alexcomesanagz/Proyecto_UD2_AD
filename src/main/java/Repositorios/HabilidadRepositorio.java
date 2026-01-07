package Repositorios;

import Entidades.Habilidad;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class HabilidadRepositorio {
    Session session;

    public HabilidadRepositorio(Session session){
        this.session = session;
    }

    public void crearHabilidad(Habilidad habilidad) {
        Transaction trx = session.beginTransaction();
        Query query = session.createQuery("SELECT MAX(id) FROM Habilidad h");
        int id = 1+ (Integer) query.getSingleResult();
        habilidad.setId(id);
        session.persist(habilidad);
        trx.commit();
        System.out.println("Habilidad creada con éxito.");
    }

    public Habilidad encontrarHabilidadPorString(String nombre){
        try{
            Query query = session.createQuery("SELECT h FROM Habilidad h WHERE h.nombre=:nombre");
            query.setParameter("nombre", nombre);
            return (Habilidad) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public void eliminarHabilidad(String nombre) {
        Transaction trx = session.beginTransaction();
        Habilidad habilidad = encontrarHabilidadPorString(nombre);
        if (habilidad != null){
            session.remove(habilidad);
            trx.commit();
            System.out.println("La habilidad se ha eliminado con éxito.");
        }else{
            trx.rollback();
            System.out.println("No se ha encontrado la habilidad con la ID proporcionada.");
        }
    }

    public void modificarPersonaje(String nombreAntiguo, String nombreNuevo) {
        Transaction trx = session.beginTransaction();
        Habilidad habilidad = encontrarHabilidadPorString(nombreAntiguo);
        if(habilidad != null){
            habilidad.setNombre(nombreNuevo);
            trx.commit();
            System.out.println("Habilidad modificada con éxito.");
        }else{
            trx.rollback();
            System.out.println("Habilidad con el ID proporcionado no existente.");
        }
    }

    public void guardarCambios(Habilidad h) {
        Transaction trx = session.beginTransaction();
        session.merge(h);
        trx.commit();
    }

    public void cantidadPersonajesPorHabilidad(String nombre) {
        Query query = session.createQuery("SELECT COUNT(p) FROM Personaje p JOIN p.listaHabilidades h WHERE h.nombre = :nombre");
        query.setParameter("nombre", nombre);
        Long cantidad = (Long) query.getSingleResult();
        System.out.println("Cantidad de personajes: " + cantidad);
    }
}
