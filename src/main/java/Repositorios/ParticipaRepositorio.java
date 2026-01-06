package Repositorios;


import Entidades.Participa;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ParticipaRepositorio {
    Session session;

    public ParticipaRepositorio(Session session){
        this.session = session;
    }

    public void guardar(Participa participa) {
        Transaction trx = session.beginTransaction();
        session.persist(participa);
        trx.commit();
    }
}
