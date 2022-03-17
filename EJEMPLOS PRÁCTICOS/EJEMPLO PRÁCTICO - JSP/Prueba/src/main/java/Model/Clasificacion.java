package Model;

import Util.Ordenacion;

public class Clasificacion {

    private DatosClasificacion[] datosClasificacions;

    // Clasificacion MANUAL
    public Clasificacion(Calendario calendario, Equipo[] equipos, int NUMEROJORNADA) {
        int numeroEquipos = equipos.length;
        datosClasificacions = new DatosClasificacion[numeroEquipos];

        for (int i = 0; i < numeroEquipos; i++) {
            datosClasificacions[i] = new DatosClasificacion();
            datosClasificacions[i].setEquipo(equipos[i]);
        }

        Jornada[] jornadas = calendario.getJornada();
        for (int i = 0; i < NUMEROJORNADA; i++) {
            Jornada jornada = jornadas[i];

            if (jornada != null && jornada.isTerminada()) {

                Partido[] partidos = jornada.getPartido();
                for (Partido partido : partidos) {
                    Equipo equipoLocal = partido.getEquipoLocal();
                    Equipo equipoVisitante = partido.getEquipoVisitante();

                    int contador = 0;
                    DatosClasificacion localClasificacion = datosClasificacions[contador];
                    while (localClasificacion.getEquipo() != equipoLocal) {
                        contador++;
                        localClasificacion = datosClasificacions[contador];
                    }

                    contador = 0;
                    DatosClasificacion visitanteClasificacion = datosClasificacions[contador];
                    while (visitanteClasificacion.getEquipo() != equipoVisitante) {
                        contador++;
                        visitanteClasificacion = datosClasificacions[contador];
                    }

                    // Goles
                    int golesLocal = partido.getGolEquipoLocal();
                    int golesVisitante = partido.getGolEquipoVisitante();

                    localClasificacion.addGolesFavor(golesLocal);
                    localClasificacion.addGolesContra(golesVisitante);
                    visitanteClasificacion.addGolesFavor(golesVisitante);
                    visitanteClasificacion.addGolesContra(golesLocal);

                    // Puntos totales
                    if (partido.getGolEquipoLocal() > partido.getGolEquipoVisitante()) {
                        localClasificacion.addPartidoGanado();
                        visitanteClasificacion.addPartidoPerdido();
                    } else if (partido.getGolEquipoLocal() < partido.getGolEquipoVisitante()) {
                        visitanteClasificacion.addPartidoGanado();
                        localClasificacion.addPartidoPerdido();
                    } else {
                        localClasificacion.addPartidoEmpatado();
                        visitanteClasificacion.addPartidoEmpatado();
                    }
                }
            }
        }

        // Reordenar la tabla - QuickSort o BubbleSort
        Ordenacion.ordenarClasificacion(datosClasificacions); // Bubblesort
        // Ordenacion.dualPivotQuickSort(datosClasificacions, 0, datosClasificacions.length-1);
    }

    // Clasificacion Automática
    public Clasificacion(Calendario calendario, Equipo[] equipos) {

        int numeroEquipos = equipos.length;
        datosClasificacions = new DatosClasificacion[numeroEquipos];

        for (int i = 0; i < numeroEquipos; i++) {
            datosClasificacions[i] = new DatosClasificacion();
            datosClasificacions[i].setEquipo(equipos[i]);
        }

        Jornada[] jornadas = calendario.getJornada();
        for (Jornada jornada : jornadas) {
            if (jornada != null && jornada.isTerminada()) {

                Partido[] partidos = jornada.getPartido();
                for (Partido partido : partidos) {
                    Equipo equipoLocal = partido.getEquipoLocal();
                    Equipo equipoVisitante = partido.getEquipoVisitante();

                    // Rafa
                    int contador = 0;
                    DatosClasificacion localClasificacion = datosClasificacions[contador];
                    while (localClasificacion.getEquipo() != equipoLocal) {
                        contador++;
                        localClasificacion = datosClasificacions[contador];
                    }

                    contador = 0;
                    DatosClasificacion visitanteClasificacion = datosClasificacions[contador];
                    while (visitanteClasificacion.getEquipo() != equipoVisitante) {
                        contador++;
                        visitanteClasificacion = datosClasificacions[contador];
                    }

                    // Goles
                    int golesLocal = partido.getGolEquipoLocal();
                    int golesVisitante = partido.getGolEquipoVisitante();

                    localClasificacion.addGolesFavor(golesLocal);
                    localClasificacion.addGolesContra(golesVisitante);
                    visitanteClasificacion.addGolesFavor(golesVisitante);
                    visitanteClasificacion.addGolesContra(golesLocal);

                    // Puntos totales
                    if (partido.getGolEquipoLocal() > partido.getGolEquipoVisitante()) {
                        localClasificacion.addPartidoGanado();
                        visitanteClasificacion.addPartidoPerdido();
                    } else if (partido.getGolEquipoLocal() < partido.getGolEquipoVisitante()) {
                        visitanteClasificacion.addPartidoGanado();
                        localClasificacion.addPartidoPerdido();
                    } else {
                        localClasificacion.addPartidoEmpatado();
                        visitanteClasificacion.addPartidoEmpatado();
                    }
                }
            }
        }

        // Reordenar la tabla - QuickSort o BubbleSort
        Ordenacion.ordenarClasificacion(datosClasificacions); // Bubblesort
        // Ordenacion.dualPivotQuickSort(datosClasificacions, 0, datosClasificacions.length-1);
    }

    public DatosClasificacion[] getDatosClasificacions() {
        return datosClasificacions;
    }

    public void setDatosClasificacions(DatosClasificacion[] datosClasificacions) {
        this.datosClasificacions = datosClasificacions;
    }

    @Override
    public String toString() {
        String cadena = "Equipo\t\t\t\t\t\t\t\t\t\t\t\t" + " J \t" + " G \t" +
                " E \t" + " P \t" + " GF \t" + "GC\t" +
                " DG\t" + "Puntos\n";
        for (DatosClasificacion eqClas : this.datosClasificacions) {
            cadena += eqClas;
        }

        return cadena;
    }
}