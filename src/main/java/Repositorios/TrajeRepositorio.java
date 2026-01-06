package Repositorios;

import Entidades.Traje;
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
}
