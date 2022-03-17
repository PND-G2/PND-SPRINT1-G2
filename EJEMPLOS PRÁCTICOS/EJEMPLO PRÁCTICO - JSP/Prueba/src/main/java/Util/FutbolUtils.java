package Util;

import Model.Entrenador;
import Model.Equipo;
import Model.Jugador;

import java.io.*;
import java.util.ArrayList;

public class FutbolUtils {

    public static void guardarEquipo(String nombreArchivo, Equipo equipo) {

        //Este método recibe un equipo y un nombre de archivo y guarda el equipo en un archivo con ese nombre

        try {
            //Se inicia un archivo con el nombre y las características.
            FileWriter writer = new FileWriter(nombreArchivo + ".txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            //Se guarda los datos del equipo
            bufferedWriter.write(equipo.toStringSinJugadores());

            // Se guarda los jugadores uno a uno
            for (int i = 0; i < equipo.getJugadores().length; i++) {
                bufferedWriter.write(equipo.getJugadores()[i].toString());
            }

            // Se cierra el documento y FIN
            bufferedWriter.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    public static Equipo cargarEquipo(String nombreArchivo) {
        Equipo equipo = new Equipo();
        Entrenador entrenador = new Entrenador();
        ArrayList<Jugador> arrayList = new ArrayList<>();

        //Este método recibe un nombre de Archivo y devuelve un equipo
        try {
            // Instanciamos el Filereader y el Buffer reader para que funcoines nuestro programa
            FileReader reader = new FileReader(nombreArchivo + ".txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            // Creamos una variable NumeroLineas para poder ir leyendo poco a poco
            String line;

            // Se carga el numero de lineas a mirar para el FOR
            while ((line = bufferedReader.readLine()) != null) {

                if (line.contains("Equipo:")) {
                    int ind = line.indexOf(":");
                    equipo.setNombre(line.substring(ind + 2));
                } else if (line.contains("Club:")) {
                    int ind = line.indexOf(":");
                    equipo.setClub(line.substring(ind + 2));
                } else if (line.contains("Entrenador:")) {

                    String siguiente = bufferedReader.readLine();

                    if (siguiente.contains("Nombre:")) {
                        int ind = siguiente.indexOf(":");
                        entrenador.setNombre(siguiente.substring(ind + 2));
                    }

                    String siguienteEdad = bufferedReader.readLine();
                    if (siguienteEdad.contains("Edad:")) {
                        int ind = siguienteEdad.indexOf(":");
                        entrenador.setEdad(Integer.parseInt(siguienteEdad.substring(ind + 2)));
                    }

                    String siguienteNumLic = bufferedReader.readLine();
                    if (siguienteNumLic.contains("Numero Licencia:")) {
                        int ind = siguienteNumLic.indexOf(":");
                        entrenador.setNumeroLicencia(Integer.parseInt(siguienteNumLic.substring(ind + 2)));
                    }
                    equipo.setEntrenador(entrenador);

                    // La parte mas dificil, meter los jugadores
                } else if (line.contains("Nombre")) {
                    Jugador jugador = new Jugador();

                    if (line.contains("Nombre:")) {
                        int ind = line.indexOf(":");

                        jugador.setNombre(line.substring(ind + 2)); // La primera palabra es el NOMBRE

                        //jugador.setApellidos(line.split(separador)); // las 2 siguientes, son los APELLIDOS

                        String siguienteEdad = bufferedReader.readLine();
                        if (siguienteEdad.contains("Edad:")) {
                            int ind2 = siguienteEdad.indexOf(":");
                            jugador.setEdad(Integer.parseInt(siguienteEdad.substring(ind2 + 2, ind2 + 4)));
                        }

                        String equipoJugador = bufferedReader.readLine();
                        if (equipoJugador.contains("Equipo:")) {

                        }
                        String dorsal = bufferedReader.readLine();

                        if (dorsal.contains("Dorsal:")) {
                            int ind3 = dorsal.indexOf(":");
                            jugador.setDorsal(Integer.parseInt(dorsal.substring(ind3 + 2)));
                        }

                        String posicion = bufferedReader.readLine();

                        if (posicion.contains("Posición:")) {
                            int ind4 = posicion.indexOf(":");
                            jugador.setPosicion(posicion.substring(ind4 + 2));
                        }


                        jugador.setEquipo(equipo);

                        arrayList.add(jugador);

                    }
                }
            }

            Jugador[] jugadores = new Jugador[arrayList.size()];

            for (int i = 0; i < arrayList.size(); i++) {
                jugadores[i] = arrayList.get(i);
            }
            equipo.setJugadores(jugadores);

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return equipo;
    }

    //Este método recibe un Equipo y ordena sus jugadores por apellidos y en caso de igualdad de apellidos por nombre
    // Era demasiado facil hacer un LIST y un SORT. AQUÍ NOS GUSTA COMPLICARNOS LA VIDA.

    public static Jugador[] ordenarEquipo(Jugador[] jugadores) {
        int n = jugadores.length;
        Jugador temp = null;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (criteriosOrdenacion(jugadores[j - 1], jugadores[j])) {
                    temp = jugadores[j - 1];
                    jugadores[j - 1] = jugadores[j];
                    jugadores[j] = temp;
                }
            }
        }

        return jugadores;
    }

    private static boolean criteriosOrdenacion(Jugador jugador1, Jugador jugador2) {
        char letraApellidoJ1 = jugador1.getApellidos().charAt(0);
        char letraApellidoJ2 = jugador2.getApellidos().charAt(0);

        char segundaLetraJ1 = jugador1.getApellidos().charAt(1);
        char segundaLetraJ2 = jugador2.getApellidos().charAt(1);

        char terceraLetraJ1 = jugador1.getApellidos().charAt(2);
        char terceraLetraJ2 = jugador2.getApellidos().charAt(2);

        char letraNombreJ1 = jugador1.getNombre().charAt(0);
        char letraNombreJ2 = jugador2.getNombre().charAt(0);

        if (letraApellidoJ1 > letraApellidoJ2) {
            return true;
        } else if (letraApellidoJ1 < letraApellidoJ2) {
            return false;
        } else if (segundaLetraJ1 > segundaLetraJ2) {
            return true;
        } else if (segundaLetraJ1 < segundaLetraJ2) {
            return false;
        } else if (terceraLetraJ1 > terceraLetraJ2) {
            return true;
        } else if (terceraLetraJ1 < terceraLetraJ2) {
            return false;
        } else {
            if (letraNombreJ1 > letraNombreJ2) {
                return true;
            } else {
                return false;
            }
        }
    }

}
