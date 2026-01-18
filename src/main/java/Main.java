import Entidades.*;
import Repositorios.*;
import org.hibernate.Session;

import java.time.LocalDate;
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
        do {
            showMenu();
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
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
        } while (opcion != 0);

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    private static void mostrarCantidadPersonajesHabilidad() {
        habilidadRepo.mostrarIdsYNombre();
        System.out.println("Introduce el ID de la habilidad:");
        int id =Integer.parseInt(sc.nextLine());
        habilidadRepo.cantidadPersonajesPorHabilidad(id);
    }

    private static void mostrarPersonajesParticiparonEnEvento() {
        eventoRepo.mostrarIdsYNombre();
        System.out.println("Introduce el nombre del evento, del cual quieras saber los personajes:");
        String nombre = sc.nextLine();
        Evento e = eventoRepo.encontrarPorNombre(nombre);
        if (e == null) {
            System.out.println("No existe ese evento");
            return;
        }
        for (Participa p : e.getParticipantes()) {
            System.out.println(p.getPersonaje().getNombre());
        }
    }

    private static void mostrarDatosPersonaje() {
        personajeRepo.mostrarIdsYNombre();
        System.out.println("Introduce la id del personaje, del cual quieras saber sus datos:");
        int id = Integer.parseInt(sc.nextLine());
        Personaje p = personajeRepo.encontrarPorId(id);
        if (p == null) {
            System.out.println("No existe el personaje con esa ID.");
            return;
        }
        System.out.println(p);
    }

    private static void cambiarTrajeDePersonaje() {
        personajeRepo.mostrarIdsYNombre();
        System.out.println("Introduce el nombre del personaje al que se le quiera cambiar el traje:");
        String nombrePersonaje = sc.nextLine();

        Personaje p = personajeRepo.encontrarPorNombre(nombrePersonaje);
        if (p == null) {
            System.out.println("El personaje no existe");
            return;
        }

        System.out.println("Introduce la especificación del nuevo traje:");
        String especificacion = sc.nextLine();

        Traje traje = trajeRepo.encontrarPorEspecificacion(especificacion);

        if (traje == null) {
            traje = new Traje(especificacion);
            trajeRepo.crearTraje(traje);
        } else {
            if (trajeRepo.estaAsignadoOtro(traje, p)) {
                System.out.println("Ese traje ya está asignado a otro personaje.");
                return;
            }
        }

        if (p.getTraje() != null && p.getTraje().getEspecificacion().equals(especificacion)) {
            System.out.println("El personaje ya tiene asignado ese traje.");
            return;
        }

        p.setTraje(traje);
        personajeRepo.guardarCambios(p);
        System.out.println("Traje cambiado con éxito.");
    }

    private static void registrarParticipacionDePersonaje() {
        personajeRepo.mostrarIdsYNombre();
        System.out.println("Introduce el nombre del personaje a participar en el evento:");
        String nombrePersonaje = sc.nextLine();
        eventoRepo.mostrarIdsYNombre();
        System.out.println("Introduce el nombre del evento en el que participa el personaje:");
        String nombreEvento = sc.nextLine();
        System.out.println("Introduce el rol del personaje:");
        String rolPersonaje = sc.nextLine();
        System.out.println("Introduce la fecha del evento:");
        LocalDate fechaEvento = LocalDate.parse(sc.nextLine());

        Evento e = eventoRepo.encontrarPorNombre(nombreEvento);
        if (e == null) {
            System.out.println("El evento no existe");
            return;
        }
        Personaje p = personajeRepo.encontrarPorNombre(nombrePersonaje);
        if (p == null) {
            System.out.println("El personaje no existe");
            return;
        }
        ParticipaId participaId = new ParticipaId(p.getId(), e.getId());
        Participa participa = new Participa(participaId, e, p, fechaEvento, rolPersonaje);
        participaRepo.guardar(participa);
        System.out.println("Participación del personaje realizada con éxito.");
    }

    private static void asignarHabilidadAPersonaje() {
        personajeRepo.mostrarIdsYNombre();
        System.out.println("Introduce el nombre del personaje: ");
        String nombrePersonaje = sc.nextLine();
        habilidadRepo.mostrarIdsYNombre();
        System.out.println("Introduce el nombre de la nueva habilidad del personaje: ");
        String nombreHabilidad = sc.nextLine();
        Personaje p = personajeRepo.encontrarPorNombre(nombrePersonaje);
        if (p == null) {
            System.out.println("El personaje no existe");
            return;
        }
        Habilidad h = habilidadRepo.encontrarHabilidadPorString(nombreHabilidad);
        if (h == null) {
            System.out.println("La habilidad no existe");
            return;
        }
        p.setHabilidadBidireccional(h);
        personajeRepo.guardarCambios(p);
        System.out.println("Habilidad asignada al personaje con éxito.");
    }

    private static void modificarHabilidad() {
        habilidadRepo.mostrarIdsYNombre();
        System.out.println("Introduce el nombre actual de la habilidad que quieras modificar: ");
        String nombreAntiguo = sc.nextLine();
        System.out.println("Introduce el nuevo nombre de la habilidad: ");
        String nombreNuevo = sc.nextLine();
        habilidadRepo.modificarPersonaje(nombreAntiguo, nombreNuevo);
    }

    private static void eliminarHabilidad() {
        habilidadRepo.mostrarIdsYNombre();
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
        personajeRepo.mostrarIdsYNombre();
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
        personajeRepo.mostrarIdsYNombre();
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