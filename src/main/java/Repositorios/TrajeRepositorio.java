package Repositorios;

import Entidades.Personaje;
import Entidades.Traje;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class TrajeRepositorio {
    Session session;

    public TrajeRepositorio(Session session){
        this.session = session;
    }

    public void crearTraje(Traje traje) {
        Transaction trx = session.beginTransaction();
        Query query = session.createQuery("SELECT MAX(id) FROM Traje t");
        int id = 1+ (Integer) query.getSingleResult();
        traje.setId(id);
        session.persist(traje);
        trx.commit();
        System.out.println("Traje creado con éxito");
    }

    public Traje encontrarPorEspecificacion(String especificacion) {
        try{
            Query query = session.createQuery("FROM Traje t WHERE t.especificacion = :esp");
            query.setParameter("esp", especificacion);
            return (Traje) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public boolean estaAsignadoOtro(Traje traje, Personaje p) {
        Query query = session.createQuery("SELECT COUNT(p) FROM Personaje p WHERE p.traje = :traje AND p != :personaje");
        query.setParameter("traje", traje);
        query.setParameter("personaje", p);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
}
