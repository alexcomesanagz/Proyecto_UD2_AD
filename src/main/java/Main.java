import Entidades.Evento;
import Entidades.ParticipaId;
import Repositorios.*;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        EventoRepositorio eventoRepo = new EventoRepositorio(session);
        HabilidadRepositorio habilidadRepo = new HabilidadRepositorio(session);
        ParticipaRepositorio participaRepo = new ParticipaRepositorio(session);
        PersonajeRepositorio personajeRepositorio = new PersonajeRepositorio(session);
        TieneHabilidadRepositorio tieneHabilidadRepo = new TieneHabilidadRepositorio(session);
        TrajeRepositorio trajeRepo = new TrajeRepositorio(session);

        //menu con funciones y pedida de datos

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

}