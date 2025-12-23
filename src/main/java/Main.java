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

        /*
        /Crear, borrar (por id) y modificar un personaje.
Crear, borrar (por nombre) y modificar una habilidad.
Asignar una habilidad a un personaje.
La asignación se hará a partir del nombre del personaje y el nombre de la habilidad.
Registrar la participación de un personaje en un evento con un rol concreto y una fecha.
Se pedirá por teclado el nombre del personaje, el nombre del evento, el rol y la fecha de participación.
Cambiar el traje de un personaje).
El método recibirá el nombre del personaje, la especificación del nuevo traje y el nuevo traje.
Mostrar los datos de un personaje (id, nombre, alias, traje, habilidades, eventos en los que ha participado con su rol y fecha).
Mostrar los personajes que participaron en un evento determinado.
Mostrar cuantos personajes tienen una habilidad concreta.
         */

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

}