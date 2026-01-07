import Entidades.*;
import Repositorios.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Locale;
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
Mostrar cuantos personajes tienen una habilidad concreta.
         */

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    private static void mostrarCantidadPersonajesHabilidad() {
        System.out.println("Introduce el nombre de la habilidad, de la cual quieras saber la cantidad de personajes que la poseen:");
        String nombre = sc.nextLine();
        habilidadRepo.cantidadPersonajesPorHabilidad(nombre);
    }

    private static void mostrarPersonajesParticiparonEnEvento() {
        System.out.println("Introduce el nombre del evento, del cual quieras saber los personajes:");
        String nombre = sc.nextLine();
        Evento e = eventoRepo.encontrarPorNombre(nombre);
        if(e == null){
            System.out.println("No existe ese evento");
            return;
        }
        for(Participa p : e.getParticipantes()){
            System.out.println(p.getPersonaje());
        }
    }

    private static void mostrarDatosPersonaje() {
        System.out.println("Introduce la id del personaje, del cual quieras saber sus datos:");
        int id = Integer.parseInt(sc.nextLine());
        Personaje p = personajeRepo.encontrarPorId(id);
        if(p == null){
            System.out.println("No existe el personaje con esa ID.");
            return;
        }
        System.out.println(p);
    }

    private static void cambiarTrajeDePersonaje() {
        System.out.println("Introduce el nombre del personaje al que se le quiera cambiar el traje:");
        String nombrePersonaje = sc.nextLine();
        System.out.println("Introduce la especificación del nuevo traje:");
        String especificacion = sc.nextLine();
        Traje traje = new Traje(especificacion);
        trajeRepo.crearTraje(traje);
        Personaje p = personajeRepo.encontrarPorNombre(nombrePersonaje);
        if(p == null){
            System.out.println("El personaje no existe");
            return;
        }
        p.setTraje(traje);
        personajeRepo.guardarCambios(p);
        System.out.println("Traje cambiado con éxito.");
    }

    private static void registrarParticipacionDePersonaje() {
        System.out.println("Introduce el nombre del personaje a participar en el evento:");
        String nombrePersonaje = sc.nextLine();
        System.out.println("Introduce el nombre del evento en el que participa el personaje:");
        String nombreEvento = sc.nextLine();
        System.out.println("Introduce el rol del personaje:");
        String rolPersonaje = sc.nextLine();
        System.out.println("Introduce la fecha del evento:");
        LocalDate fechaEvento = LocalDate.parse(sc.nextLine());

        Evento e = eventoRepo.encontrarPorNombre(nombreEvento);
        if(e == null){
            System.out.println("El evento no existe");
            return;
        }
        Personaje p = personajeRepo.encontrarPorNombre(nombrePersonaje);
        if(p == null){
            System.out.println("El personaje no existe");
            return;
        }
        ParticipaId participaId = new ParticipaId(p.getId(), e.getId());
        Participa participa = new Participa(participaId, e, p, fechaEvento , rolPersonaje);
        participaRepo.guardar(participa);
        System.out.println("Participación del personaje realizada con éxito.");
    }

    private static void asignarHabilidadAPersonaje() {
        System.out.println("Introduce el nombre del personaje que quieras asignarle una nueva habilidad: ");
        String nombrePersonaje = sc.nextLine();
        System.out.println("Introduce el nombre de la nueva habilidad a la que quieras asignarle un nuevo personaje: ");
        String nombreHabilidad = sc.nextLine();
        Personaje p = personajeRepo.encontrarPorNombre(nombrePersonaje);
        if(p == null){
            System.out.println("El personaje no existe");
            return;
        }
        Habilidad h = habilidadRepo.encontrarHabilidadPorString(nombreHabilidad);
        if(h == null){
            System.out.println("La habilidad no existe");
            return;
        }
        p.setHabilidadBidireccional(h);
        personajeRepo.guardarCambios(p);
        System.out.println("Habilidad asignada al personaje con éxito.");
    }

    private static void modificarHabilidad() {
        System.out.println("Introduce el nombre actual de la habilidad que quieras modificar: ");
        String nombreAntiguo = sc.nextLine();
        System.out.println("Introduce el nuevo nombre de la habilidad: ");
        String nombreNuevo = sc.nextLine();
        habilidadRepo.modificarPersonaje(nombreAntiguo, nombreNuevo);
        System.out.println("Habilidad modificada con éxito.");
    }

    private static void eliminarHabilidad() {
        System.out.println("Introduce el nombre de la habilidad que quieras eliminar: ");
        String nombre = sc.nextLine();
        habilidadRepo.eliminarHabilidad(nombre);
        System.out.println("Habilidad eliminada con éxito.");
    }

    private static void crearHabilidad() {
        System.out.println("Introduce el nombre de la habilidad que quieras crear: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce la descripcion de la habilidad que quieras crear: ");
        String descripcion = sc.nextLine();
        habilidadRepo.crearHabilidad(new Habilidad(nombre, descripcion));
        System.out.println("Habilidad creada con éxito.");
    }

    public static void modificarPersonaje() {
        System.out.println("Introduce el id del personaje que quieras modificar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el nuevo nombre del personaje: ");
        String nombre = sc.nextLine();
        personajeRepo.modificarPersonaje(id, nombre);
        System.out.println("Personaje modificado con éxito.");
    }

    public static void crearPersonaje() {
        System.out.println("Introduce el nombre del personaje que quieras crear: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce el alias del personaje que quieras crear: ");
        String alias = sc.nextLine();
        personajeRepo.crearPersonaje(new Personaje(nombre, alias));
        System.out.println("Personaje creado con éxito.");
    }

    public static void eliminarPersonaje() {
        System.out.println("Introduce el id del personaje que quieras eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        personajeRepo.eliminarPersonaje(id);
        System.out.println("Personaje eliminado con éxito.");
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