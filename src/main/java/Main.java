import Entidades.Evento;
import Entidades.ParticipaId;
import Entidades.Personaje;
import Repositorios.*;
import org.hibernate.Session;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        EventoRepositorio eventoRepo = new EventoRepositorio(session);
        HabilidadRepositorio habilidadRepo = new HabilidadRepositorio(session);
        ParticipaRepositorio participaRepo = new ParticipaRepositorio(session);
        PersonajeRepositorio personajeRepo = new PersonajeRepositorio(session);
        TieneHabilidadRepositorio tieneHabilidadRepo = new TieneHabilidadRepositorio(session);
        TrajeRepositorio trajeRepo = new TrajeRepositorio(session);

        //menu con funciones y pedida de datos

        int opcion;
        do{
            showMenu();
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion){
                case 1 -> crearPersonaje();
            }
        }while(opcion!=0);

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

    private static void crearPersonaje() {
        System.out.println("");
        String nombre = sc.nextLine();
        System.out.println("");
        String alias = sc.nextLine();
        personajeRepo.crearPersonaje( new Personaje(nombre, alias) );
    }

    private static void showMenu() {
    }

}