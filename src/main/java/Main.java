import Entidades.Evento;
import Entidades.HabilidadId;
import Entidades.ParticipaId;
import Entidades.Personaje;
import Repositorios.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static EventoRepositorio eventoRepo;
    private static HabilidadRepositorio habilidadRepo;
    private static ParticipaRepositorio participaRepo;
    private static PersonajeRepositorio personajeRepo;
    private static TieneHabilidadRepositorio tieneHabilidadRepo;
    private static TrajeRepositorio trajeRepo;

    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        eventoRepo = new EventoRepositorio(session);
        habilidadRepo = new HabilidadRepositorio(session);
        participaRepo = new ParticipaRepositorio(session);
        personajeRepo = new PersonajeRepositorio(session);
        tieneHabilidadRepo = new TieneHabilidadRepositorio(session);
        trajeRepo = new TrajeRepositorio(session);

        //menu con funciones y pedida de datos

        int opcion;
        do{
            showMenu();
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion){
                case 1 -> crearPersonaje();
                case 2 -> eliminarPersonaje();
                case 3 -> modificarPersonaje();
                case 4 -> crearHabilidad();
                case 5 -> eliminarHabilidad();
                case 6 -> modificarHabilidad();
//                case 1 -> crearPersonaje();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println();
            }
        }while(opcion!=0);

        /*
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

    private static void modificarHabilidad() {
        System.out.println("Introduce el nombre actual de la habilidad que quieras modificar: ");
        String nombreAntiguo = sc.nextLine();
        System.out.println("Introduce el nuevo nombre de la habilidad: ");
        String nombreNuevo = sc.nextLine();
        habilidadRepo.modificarPersonaje(nombreAntiguo, nombreNuevo);
    }

    private static void eliminarHabilidad() {
        System.out.println("Introduce el nombre de la habilidad que quieras eliminar: ");
        String nombre = sc.nextLine();
        habilidadRepo.eliminarHabilidad(nombre);
    }

    private static void crearHabilidad() {
        System.out.println("Introduce el nombre de la habilidad que quieras crear: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce la descripcion de la habilidad que quieras crear: ");
        String descripcion = sc.nextLine();
        habilidadRepo.crearHabilidad(nombre, descripcion);
    }

    public static void modificarPersonaje() {
        System.out.println("Introduce el id del personaje que quieras modificar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el nuevo nombre del personaje: ");
        String nombre = sc.nextLine();
        personajeRepo.modificarPersonaje(id, nombre);
    }

    public static void crearPersonaje() {
        System.out.println("Introduce el nombre del personaje que quieras crear: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce el alias del personaje que quieras crear: ");
        String alias = sc.nextLine();
        personajeRepo.crearPersonaje(new Personaje(nombre, alias));
    }

    public static void eliminarPersonaje() {
        System.out.println("Introduce el id del personaje que quieras eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        personajeRepo.eliminarPersonaje(id);
    }

    private static void showMenu() {
        System.out.println("--- SELECCIONA UNA DE LAS SISGUIENTES OPCIONES: ---");
        System.out.println("1. Crear un personaje");
        System.out.println("2. Eliminar un personaje");
        System.out.println("3. Modificar un personaje");
        System.out.println("4. ...");
        System.out.println("---------------------------------------------------");
    }

}