package View;

import Model.*;
import Util.Ordenacion;
import Util.Repositorio;

import java.util.Scanner;

public class Principal {

    // Valores globales de la Liga Automática
    private static final Liga liga = new Liga();
    private static final Equipo[] listaEquipos = liga.getEquipos();
    private static final Arbitro[] listaArbitros = liga.getArbitros();
    private static final Calendario calendario = liga.getCalendario();
    private static final Clasificacion clasificacion = liga.getClasificacion();

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        limpiarPantalla();
        System.out.println(Repositorio.menu);
        Scanner scanner = new Scanner(System.in);
        int entrada = scanner.nextInt();

        switch (entrada) {
            case 1 -> verListadoEquipos(listaEquipos);
            case 2 -> verCalendario(calendario);
            case 3 -> verGoles(listaEquipos);
            case 4 -> verMejoresPorteros(listaEquipos);
            case 5 -> verlesionados(listaEquipos);
            case 6 -> crearAlineacion();
            case 7 -> modificarJornada(calendario.getJornada());
            case 8 -> crearNuevaLiga();
            case 9 -> verClasificacion(clasificacion);
            case 0 -> {
                System.out.println("See you space cowboy");
                System.exit(0);
            }
            default -> {
                System.out.println("***ATENCIÓN! Ese número no existe en el menú.***");
                esperaPulsacion();
                menuPrincipal();
            }
        }
    }

    private static void verClasificacion(Clasificacion clasificacion) {
        System.out.println(clasificacion.toString());
        esperaPulsacion();
        menuPrincipal();
    }

    private static void verListadoEquipos(Equipo[] listaEquipos) {
        System.out.println(
                " Listado de Equipos de la Liga: " + liga.getNombre() + "\n" +
                        " \n" +
                        " ¿Qué equipo quieres ver de entre los " + listaEquipos.length + " que existen? \n");

        for (int i = 0; i < listaEquipos.length; i++) {
            System.out.println("    " + (i + 1) + ") " + listaEquipos[i].getNombre());
        }
        System.out.println("    " + (listaEquipos.length + 1) + ") Salir ");

        Scanner scanner = new Scanner(System.in);
        System.out.print("-> ");
        int entrada = scanner.nextInt() - 1;

        if (entrada >= 0 && entrada < listaEquipos.length) {
            verEquipo(listaEquipos[entrada]);
        } else if (entrada == listaEquipos.length) {
            menuPrincipal();
        } else {
            limpiarPantalla();
            System.out.println("El número introducido no está en la lista.");
            System.out.println("Vuelva a introducir un numero de la lista");
            verListadoEquipos(listaEquipos);
        }
    }

    private static void verEquipo(Equipo equipo) {
        System.out.println("\nDetalles del Equipo:");
        System.out.println(equipo.toStringSinJugadores());

        System.out.println("\nEstos son los jugadores del Equipo\n");
        for (int i = 0; i < equipo.getJugadores().length; i++) {
            System.out.println(" " + (i + 1) + " " + equipo.getJugadores()[i].getNombre() + " " + equipo.getJugadores()[i].getApellidos() + " \t Posicion: " + equipo.getJugadores()[i].getPosicion() + " \t Dorsal: " + equipo.getJugadores()[i].getDorsal());
        }

        System.out.println("""
                \n\n¿Quieres modificar algún valor?
                1) - Modificar Datos del Equipo
                2) - Modificar Datos del Entrenador
                3) - Modificar Datos de un Jugador
                0) - Salir
                			""");
        Scanner scanner = new Scanner(System.in);
        System.out.print("-> ");
        int entrada = scanner.nextInt();

        switch (entrada) {
            case 1:
                modificarDatosEquipo(equipo);
            case 2:
                modificarDatosEntrenador(equipo);
            case 3:
                modificarDatosJugadores(equipo);
            case 0:
                menuPrincipal();
            default: {
                System.out.println("***ATENCIÓN! Ese número no existe en el menú.***");
                esperaPulsacion();
                menuPrincipal();
            }
        }
    }

    private static void modificarDatosJugadores(Equipo equipo) {
        limpiarPantalla();

        System.out.println("\nSelecciona un Jugador\n");

        for (int i = 0; i < equipo.getJugadores().length; i++) {
            System.out.println(" " + (i + 1) + " " + equipo.getJugadores()[i].getNombre() + " " + equipo.getJugadores()[i].getApellidos() + " \t Posicion: " + equipo.getJugadores()[i].getPosicion() + " \t Dorsal: " + equipo.getJugadores()[i].getDorsal());
        }

        int jugadorSeleccionado = scanner();

        if (jugadorSeleccionado != 0 && jugadorSeleccionado < equipo.getJugadores().length) {

            System.out.println("\nSi no escribes nada, no se cambia el nombre." +
                    "\nNuevo nombre del Jugador:");
            String nuevoNombreJugador = scannerTexto();
            System.out.println("Apellidos");
            String nuevoApellidosJugador = scannerTexto();
            if (nuevoNombreJugador.length() > 0) {
                equipo.getJugadores()[jugadorSeleccionado].setNombre(nuevoNombreJugador);
                equipo.getJugadores()[jugadorSeleccionado].setApellidos(nuevoApellidosJugador);
            }

            System.out.println("Nombre: " + equipo.getJugadores()[jugadorSeleccionado].getNombre() + " " + equipo.getJugadores()[jugadorSeleccionado].getApellidos());

            System.out.println("Nueva Edad:");
            int nuevaEdadJugador = scanner();
            if (nuevaEdadJugador != 0 && nuevaEdadJugador > 0)
                equipo.getJugadores()[jugadorSeleccionado].setEdad(nuevaEdadJugador);

            System.out.println("Edad: " + equipo.getJugadores()[jugadorSeleccionado].getEdad());

            System.out.println("""
                    \nPosiciones:
                    1) Portero
                    2) Defensa
                    3) Centro Campista
                    4) Delantero
                    """);

            int nuevaPosicionJugador = scanner();
            if (nuevaPosicionJugador != 0 && nuevaPosicionJugador > 0 && nuevaPosicionJugador < 5) {
                if (nuevaPosicionJugador == 1) equipo.getJugadores()[jugadorSeleccionado].setPosicion("Portero");
                if (nuevaPosicionJugador == 2) equipo.getJugadores()[jugadorSeleccionado].setPosicion("Defensa");
                if (nuevaPosicionJugador == 3)
                    equipo.getJugadores()[jugadorSeleccionado].setPosicion("Centro Campista");
                if (nuevaPosicionJugador == 4) equipo.getJugadores()[jugadorSeleccionado].setPosicion("Delantero");
            }
            System.out.println("Nueva Posición: " + equipo.getJugadores()[jugadorSeleccionado].getPosicion());

            System.out.println("Nuevo Dorsal:");
            int nuevoDorsal = scanner();
            if (nuevoDorsal != 0 && nuevoDorsal > 0) {
                equipo.getJugadores()[jugadorSeleccionado].setDorsal(nuevoDorsal);
            }
            System.out.println("Dorsal: " + equipo.getJugadores()[jugadorSeleccionado].getDorsal());
        }

        esperaPulsacion();
        verListadoEquipos(listaEquipos);
    }

    private static void modificarDatosEntrenador(Equipo equipo) {
        limpiarPantalla();
        System.out.println("\nSi no escribes nada, no se cambia el nombre." +
                "\nNuevo nombre del Entrenador:");
        String nuevoNombreEntrenador = scannerTexto();
        System.out.println("Apellidos");
        String nuevoApellidosEntrenador = scannerTexto();
        if (nuevoNombreEntrenador.length() > 0) {
            equipo.getEntrenador().setNombre(nuevoNombreEntrenador);
            equipo.getEntrenador().setApellidos(nuevoApellidosEntrenador);
        }
        System.out.println("Nombre: " + equipo.getEntrenador().getNombre() + " " + equipo.getEntrenador().getApellidos());

        System.out.println("Nueva Edad:");
        int nuevaEdadEntrenador = scanner();
        if (nuevaEdadEntrenador != 0 && nuevaEdadEntrenador > 0) {
            equipo.getEntrenador().setEdad(nuevaEdadEntrenador);
        }

        System.out.println("Edad: " + equipo.getEntrenador().getEdad());

        System.out.println("Nuevo Numero de Licencia:");
        int nuevoNumeroLicencia = scanner();
        if (nuevoNumeroLicencia != 0 && nuevoNumeroLicencia > 0) {
            equipo.getEntrenador().setNumeroLicencia(nuevoNumeroLicencia);
        }
        System.out.println("Numero Licencia: " + equipo.getEntrenador().getNumeroLicencia());

        esperaPulsacion();
        verListadoEquipos(listaEquipos);
    }

    private static void modificarDatosEquipo(Equipo equipo) {
        limpiarPantalla();
        System.out.println("\nSi no escribes nada, no se cambia el nombre." +
                "\nNuevo nombre del Equipo:");

        String nuevoNombreEquipo = scannerTexto();
        if (nuevoNombreEquipo.length() > 0) {
            equipo.setNombre(nuevoNombreEquipo);
        }
        System.out.println("Nombre Equipo: " + equipo.getNombre());

        System.out.println("\nNuevo nombre del Club");
        String nuevoNombreClub = scannerTexto();
        if (nuevoNombreClub.length() > 0) {
            equipo.setClub(nuevoNombreClub);
        }
        System.out.println("Nombre del Club: " + equipo.getClub());

        esperaPulsacion();
        verListadoEquipos(listaEquipos);
    }

    private static void verCalendario(Calendario calendario) {
        Jornada[] jornadas = calendario.getJornada();

        System.out.println("Listado de Jornadas de la Liga \n" +
                " \n" +
                "¿Qué número de jornada quieres ver? \n");

        for (int i = 0; i < jornadas.length; i++) {
            System.out.println("    Jornada nº" + (i + 1) + ") " + jornadas[i].getNumeroJornada() + " " + jornadas[i].getFecha());

        }
        System.out.println("    " + (jornadas.length + 1) + ") Salir ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione la jornada que desea ver: ");
        System.out.print("-> ");

        int entradaJornada = (scanner.nextInt() - 1);

        if (entradaJornada >= 0 && entradaJornada < jornadas.length) {
            limpiarPantalla();
            verJornada(jornadas[entradaJornada]);
        } else if (entradaJornada == jornadas.length) {
            limpiarPantalla();
            menuPrincipal();
        } else {
            limpiarPantalla();
            System.out.println("El número introducido no está en la lista.");
            System.out.println("Vuelva a introducir un numero de la lista");
            verCalendario(calendario);
        }

        esperaPulsacion();
        menuPrincipal();
    }

    private static void verJornada(Jornada jornada) {
        System.out.println("Estos son los partidos de la Jornada " + jornada.getNumeroJornada() + "\n\n");
        for (int i = 0; i < jornada.getPartido().length; i++) {
            System.out.println(" " + (i + 1) + " " + jornada.getPartido()[i]);
        }
    }

    private static void verGoles(Equipo[] listaEquipos) {
        int numeroGoleadores = 0;
        for (Equipo equipo : listaEquipos) {
            for (Jugador jugador : equipo.getJugadores()) {
                numeroGoleadores++;
            }
        }

        Jugador[] maximosGoleadores = new Jugador[(numeroGoleadores)];

        int contador = 0;
        for (Equipo equipo : listaEquipos) {
            Jugador[] jugadores = equipo.getJugadores();
            for (Jugador jugadors : jugadores) {
                maximosGoleadores[contador] = jugadors;
                contador++;
            }
        }

        for (Jugador j : Ordenacion.ordenarJugadores(maximosGoleadores, "Goleadores")) {
            if (j.getGol() > 0) {
                System.out.println("Nombre-> " + j.getNombre() + " " + j.getApellidos() + " Goles-> " + j.getGol() + " Equipo:" + j.getEquipo().getNombre());
            }
        }

        esperaPulsacion();
        menuPrincipal();
    }

    private static void verMejoresPorteros(Equipo[] listaEquipos) {
        int numeroPortero = 0;
        for (Equipo equipo : listaEquipos) {
            for (Jugador jugador : equipo.getJugadores()) {
                if (jugador.getPosicion().equals("Portero")) numeroPortero++;
            }
        }

        Jugador[] mejoresPortero = new Jugador[(numeroPortero)];
        int minimoPartidos = listaEquipos.length - 1;

        int contador = 0;
        for (Equipo equipo : listaEquipos) {
            Jugador[] jugadores = equipo.getJugadores();
            for (Jugador jugadors : jugadores) {
                if (jugadors.getPosicion().equals("Portero")) {
                    mejoresPortero[contador] = jugadors;
                    contador++;
                }
            }
        }

        for (Jugador j : Ordenacion.ordenarJugadores(mejoresPortero, "Portero")) {
            if (j.getPartidoJugados() >= minimoPartidos) {
                System.out.println("Nombre-> " + j.getNombre() + " " + j.getApellidos() + " Posicion -> " + j.getPosicion() + " Goles Encajados -> " + j.getGolEncajados() + " Equipo:" + j.getEquipo().getNombre());
            }
        }

        esperaPulsacion();
        menuPrincipal();
    }

    private static void verlesionados(Equipo[] listaEquipos) {
        Scanner sc = new Scanner(System.in);

        System.out.println("¿De qué jornada desea ver los lesionados?:" +
                "\n Valor entre 1 y " + liga.getCalendario().getJornada().length);
        int numeroJornada = sc.nextInt();

        if (numeroJornada != 0 && numeroJornada > -1
                && numeroJornada < liga.getCalendario().getJornada().length) {

            Clasificacion clasificacionTemporal = new Clasificacion(liga.getCalendario(), listaEquipos, numeroJornada);

            Equipo[] listadoTemp = new Equipo[listaEquipos.length];

            for (int i = 0; i < listaEquipos.length; i++) {
                listadoTemp[i] = clasificacionTemporal.getDatosClasificacions()[i].getEquipo();
            }

            for (Equipo equipoTemp : listadoTemp) {
                for (Jugador jugador : equipoTemp.getJugadores()) {
                    if (jugador.getDuracionLesion() > 0) {
                        System.out.println("Nombre-> " + jugador.getNombre() + " " + jugador.getApellidos() + " Duracion Lesion: " + jugador.getDuracionLesion());
                    }
                }
            }
        }

        esperaPulsacion();
        menuPrincipal();
    }

    public static void crearAlineacion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("A que equipo le quiere poner la alineación\n");
        for (int i = 0; i < listaEquipos.length; i++) {
            System.out.println(" " + (i + 1) + ") " + listaEquipos[i].getNombre());
        }
        System.out.println(" " + (listaEquipos.length + 1) + ") Salir ");

        int opcionEquipo = (scanner.nextInt() - 1);

        if (opcionEquipo >= 0 && opcionEquipo < listaEquipos.length) {
            System.out.println("Seleccione la alineacion que desee: ");

            for (int i = 0; i < Repositorio.formaciones.length; i++) {
                System.out.println("Opción " + (i + 1) + ": " + Repositorio.formaciones[i]);
            }
            System.out.println("Opcion " + (listaEquipos.length + 1) + " Salir ");

            int opcion = (scanner.nextInt() - 1);

            if (opcion >= 0 && opcion < Repositorio.formaciones.length) {

                Alineacion alineacionx = new Alineacion(listaEquipos[opcionEquipo].getJugadores(), opcion);

                System.out.println(alineacionx);

            } else if (opcion == listaEquipos.length) {
                menuPrincipal();
            } else {
                limpiarPantalla();
                System.out.println("El número introducido no está en la lista.");
                System.out.println("Vuelva a introducir un numero de la lista. \n");
                crearAlineacion();
            }
        } else if (opcionEquipo == listaEquipos.length) {
            menuPrincipal();
        } else {
            limpiarPantalla();
            System.out.println("El número introducido no está en la lista.");
            System.out.println("Vuelva a introducir un numero de la lista. \n");
            crearAlineacion();
        }

        esperaPulsacion();
        menuPrincipal();
    }

    private static void modificarJornada(Jornada[] jornadas) {
        limpiarPantalla();

        System.out.println("Introduce el número de jornada a seleccionar: 1 - " + jornadas.length);
        int jornada = scanner() - 1;

        Partido[] partidos = new Partido[jornadas[jornada].getPartido().length];
        if (jornada >= 0 && jornada < jornadas.length) {
            // Bucle que recorre los partidos
            for (int i = 0; i < partidos.length; i++) {
                System.out.println("Partido numero " + (i + 1) + jornadas[jornada].getPartido()[i]);

                System.out.println("Nuevo resultado local:");
                int nuevoResultado = scanner();
                jornadas[jornada].getPartido()[i].setGolEquipoLocal(nuevoResultado);

                System.out.println("Nuevo resultado visitante:");
                nuevoResultado = scanner();
                jornadas[jornada].getPartido()[i].setGolEquipoVisitante(nuevoResultado);

                System.out.println("NUevos resultados");
                System.out.println(jornadas[jornada].getPartido()[i].toString());
            }
        } else if (jornada == jornadas.length + 1) {
            menuPrincipal();
        } else {
            limpiarPantalla();
            System.out.println("El número introducido no está en la lista.");
            modificarJornada(jornadas);
        }

        menuPrincipal();
    }

    private static void crearNuevaLiga() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el nombre de la liga: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce el número de equipos (Entre 4-20): ");
        int numequipos = scanner.nextInt();
        System.out.print("Introduce la edad de los jugadores (Entre 4-18): ");
        int edadjug = scanner.nextInt();
        System.out.print("Introduce el número de jugadores (Entre 20-253): ");
        int numJugadores = scanner.nextInt();

        Liga liga = new Liga(nombre, numequipos, edadjug, numJugadores, (numequipos - 1) * 2);

        menuPrincipal();
    }

    public static void esperaPulsacion() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("***Pulse enter para continuar en el programa. Gracias***");
        sc.nextLine();
    }

    private static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) System.out.println("");
    }

    private static void pausaPantalla() {
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    private static int scanner() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private static String scannerTexto() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
