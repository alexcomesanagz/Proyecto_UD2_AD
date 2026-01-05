import Entidades.*;
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
    private static TrajeRepositorio trajeRepo;

    public static void main(String[] args) {
        Session session = HibernateUtil.get().openSession();

        eventoRepo = new EventoRepositorio(session);
        habilidadRepo = new HabilidadRepositorio(session);
        participaRepo = new ParticipaRepositorio(session);
        personajeRepo = new PersonajeRepositorio(session);
        trajeRepo = new TrajeRepositorio(session);

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
                case 7 -> asignarHabilidadAPersonaje();
                case 8 -> registrarParticipacionDePersonaje();
                case 9 -> cambiarTrajeDePersonaje();
                case 10 -> mostrarDatosPersonaje();
                case 11 -> mostrarPersonajesParticiparonEnEvento();
                case 12 -> mostrarCantidadPersonajesHabilidad();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println();
            }
        }while(opcion!=0);

        /*
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

    private static void mostrarCantidadPersonajesHabilidad() {
    }

    private static void mostrarPersonajesParticiparonEnEvento() {

    }

    private static void mostrarDatosPersonaje() {

    }

    private static void cambiarTrajeDePersonaje() {

    }

    private static void registrarParticipacionDePersonaje() {
    }

    private static void asignarHabilidadAPersonaje() {
//        Asignar una habilidad a un personaje.
//        La asignación se hará a partir del nombre del personaje y el nombre de la habilidad.
        System.out.println("Introduce el nombre del personaje que quieras asignarle una nueva habilidad: ");
        String nombrePersonaje = sc.nextLine();
        System.out.println("Introduce el nombre de la nueva habilidad a la que quieras asignarle un nuevo personaje: ");
        String nombreHabilidad = sc.nextLine();

//        Personaje.setHabilidadBidireccional(habilidadRepo.conseguirHabilidadPorNombre(nombreHabiliad));
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
        habilidadRepo.crearHabilidad(new Habilidad(nombre, descripcion));
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
        System.out.println("4. Crear una habilidad");
        System.out.println("5. Eliminar una habilidad");
        System.out.println("6. Modificar una habilidad");
        System.out.println("7. Asignar una habilidad a un personaje");
        System.out.println("8. Registrar la participación de un personaje en un evento");
        System.out.println("9. Cambiar el traje de un personaje");
        System.out.println("10. Mostrar datos de un personaje");
        System.out.println("11. Mostrar personaje que participaron en un evento");
        System.out.println("12. Mostrar cantidad de personajes que tienen una habilidad");
        System.out.println("---------------------------------------------------");
    }

}