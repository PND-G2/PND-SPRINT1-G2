package Controller;

import Model.*;
import Util.*;

import java.util.*;

public class Generador {

    private static String generadorAleatorioNombres() {
        return Repositorio.nombres[(int) Math.floor(Math.random() * Repositorio.nombres.length)];
    }

    private static String generadorAleatorioApellidos() {
        return Repositorio.apellidos[(int) Math.floor(Math.random() * Repositorio.apellidos.length)] + " " + Repositorio.apellidos[(int) Math.floor(Math.random() * Repositorio.apellidos.length)];
    }

    private static Jugador[] crearJugadores(int numeroJugadores, int edad, Equipo equipo) {
        ArrayList<String> posiciones = new ArrayList<>(Arrays.asList(Repositorio.listadoPosiciones));
        ArrayList<Integer> dorsales = new ArrayList<>(List.of(Repositorio.listadoDorsales));

        Jugador[] jugadores = new Jugador[numeroJugadores];

        for (int i = 0; i < numeroJugadores; i++) {
            Jugador jugadorTemporal = new Jugador();
            jugadorTemporal.setPosicion(posiciones.get(0));
            posiciones.remove(0);

            jugadorTemporal.setNombre(Generador.generadorAleatorioNombres());// esto no
            jugadorTemporal.setApellidos(Generador.generadorAleatorioApellidos());

            jugadorTemporal.setEdad(edad);

            jugadorTemporal.setDorsal(dorsales.get(0));
            dorsales.remove(0);

            jugadorTemporal.setEquipo(equipo);

            jugadores[i] = jugadorTemporal;
        }

        return jugadores;
    }
    
    public static Jugador[] crearJugadores(int numeroJugadores, int edad) {
        ArrayList<String> posiciones = new ArrayList<>(Arrays.asList(Repositorio.listadoPosiciones));
        ArrayList<Integer> dorsales = new ArrayList<>(List.of(Repositorio.listadoDorsales));

        Jugador[] jugadores = new Jugador[numeroJugadores];

        for (int i = 0; i < numeroJugadores; i++) {
            Jugador jugadorTemporal = new Jugador();
            jugadorTemporal.setPosicion(posiciones.get(0));
            posiciones.remove(0);

            jugadorTemporal.setNombre(Generador.generadorAleatorioNombres());// esto no
            jugadorTemporal.setApellidos(Generador.generadorAleatorioApellidos());

            jugadorTemporal.setEdad(edad);

            jugadorTemporal.setDorsal(dorsales.get(0));
            dorsales.remove(0);

            jugadores[i] = jugadorTemporal;
        }

        return jugadores;
    }


    private static Entrenador crearEntrenador(Equipo equipo) {
        Entrenador entrenador = new Entrenador();
        entrenador.setEquipo(equipo);

        entrenador.setNombre(Generador.generadorAleatorioNombres());
        entrenador.setApellidos(Generador.generadorAleatorioApellidos());

        int edad = (int) Math.floor(Math.random() * 47) + 18;
        entrenador.setEdad(edad);

        int licencia = (int) Math.floor(Math.random() * 100000);
        entrenador.setNumeroLicencia(licencia);

        return entrenador;
    }

    public static Arbitro[] crearArbitro(int numeroEquipos) {
        Arbitro[] arbitro = new Arbitro[numeroEquipos / 2];

        for (int i = 0; i < numeroEquipos / 2; i++) {
            Arbitro arbitroTemporal = new Arbitro();

            arbitroTemporal.setNombre(Generador.generadorAleatorioNombres());
            arbitroTemporal.setApellidos(Generador.generadorAleatorioApellidos());

            int edad = (int) Math.floor(Math.random() * 47) + 18;
            arbitroTemporal.setEdad(edad);

            int licencia = (int) Math.floor(Math.random() * 100000);
            arbitroTemporal.setLicencia(licencia);

            arbitro[i] = arbitroTemporal;
        }
        return arbitro;
    }

    public static Equipo[] crearEquipos(int numeroEquipos, int edad, int numeroJugadores) {
        Equipo[] listaEquipos = new Equipo[numeroEquipos];

        ArrayList<String> barriosArraylist = new ArrayList<>(Arrays.asList(Repositorio.nombreBarrios));
        ArrayList<String> mascotasArraylist = new ArrayList<>(Arrays.asList(Repositorio.mascotas));

        for (int i = 0; i < numeroEquipos; i++) {
            Equipo equipo = new Equipo();

            int numero = (int) Math.floor(Math.random() * barriosArraylist.size());
            String barrio = barriosArraylist.get(numero);
            barriosArraylist.remove(numero);

            numero = (int) Math.floor(Math.random() * mascotasArraylist.size());
            String mascota = mascotasArraylist.get(numero);
            mascotasArraylist.remove(numero);

            equipo.setClub(barrio + " F.C.");

            String nombre;
            if (barrio.startsWith("El ")) {
                barrio = barrio.substring(3);
                nombre = mascota + " del " + barrio;
            } else {
                nombre = mascota + " de " + barrio;
            }

            equipo.setNombre(nombre);

            Entrenador entrenador = crearEntrenador(equipo);
            equipo.setEntrenador(entrenador);

            Jugador[] jugadores = crearJugadores(numeroJugadores, edad, equipo);
            equipo.setJugadores(jugadores);

            listaEquipos[i] = equipo;
        }
        return listaEquipos;
    }

    public static Partido jugarPartido(Partido partido) {
        int formaciones = Repositorio.formaciones.length;
        int aleatoriaLocal = (int) (Math.random() * (formaciones - 1) + 0); // un numero
        Alineacion alineacionLocal = new Alineacion(partido.getEquipoLocal().getJugadores(), aleatoriaLocal);
        alineacionLocal.setEntrenador(partido.getEquipoLocal().getEntrenador());
        partido.setAlineacionLocales(alineacionLocal);

        int aleatoriaVisitante = (int) (Math.random() * (formaciones - 1) + 0);  // segundo numero
        Alineacion alineacionVisitante = new Alineacion(partido.getEquipoVisitante().getJugadores(), aleatoriaVisitante);
        alineacionVisitante.setEntrenador(partido.getEquipoVisitante().getEntrenador());
        partido.setAlineacionVisitantes(alineacionVisitante);

        Jugador[] jugadoresLocal = partido.getAlineacionLocales().getTitulares();
        Jugador[] jugadoresVisitante = partido.getAlineacionVisitantes().getTitulares();

        int golesLocales = Aleatorio.generadorRandom();
        int golesVisitantes = Aleatorio.generadorRandom();

        partido.setGolEquipoLocal(golesLocales);
        partido.setGolEquipoVisitante(golesVisitantes);

        for (int k = 0; k < partido.getGolEquipoLocal(); k++) {
            int goleador = (int) (Math.random() * 10 + 0);
            Jugador jugadorGol = jugadoresLocal[goleador];
            jugadorGol.setGol(1);
        }

        for (int k = 0; k < partido.getGolEquipoVisitante(); k++) {
            int goleador = (int) (Math.random() * 10 + 0);
            Jugador jugadorGol = jugadoresVisitante[goleador];
            jugadorGol.setGol(1);
        }

        // Jugar el partido
        //Añadir un partido jugado más
        for (int p = 0; p < jugadoresLocal.length; p++) {
            jugadoresLocal[p].addPartidoJugados();
            jugadoresVisitante[p].addPartidoJugados();
        }


        // Goles al jugador que ha jugado de portero
        jugadoresLocal[0].setGolEncajados(golesLocales);
        jugadoresVisitante[0].setGolEncajados(golesVisitantes);

        /* Generamos las lesiones */
        generarLesiones(jugadoresLocal);
        generarLesiones(jugadoresVisitante);

        return partido;
    }

    public static void generarLesiones(Jugador[] jugadores) {
        for (int i = 0; i < jugadores.length; i++) {
            int tiempoLesion = Aleatorio.probablidadLesion();
            if (tiempoLesion != 0) {
                jugadores[i].setDuracionLesion(tiempoLesion);
            }
        }
    }

}