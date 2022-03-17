package Model;

import Controller.Generador;

import java.util.*;

public class Jornada {

    private int numeroJornada;
    private Date Fecha;
    private Partido[] partido;
    private boolean terminada = false;


    public Jornada(Equipo[] listaEquipos, Arbitro[] listaArbritos, int numeroJornada, Calendar calendar, boolean Automatico) {

        // Se crean los partidos y se setean SOLAMENTE los equipos LOCALES y VISITANTES.
        this.partido = crucesEquipos(listaEquipos, numeroJornada);

        ArrayList<Arbitro> arbitroArrayList = new ArrayList<>(Arrays.asList(listaArbritos));

        for (int i = 0; i < this.partido.length; i++) {
            //Seguimos rellenando la informacion de cada partido
            Partido partido = this.partido[i];

            // Fecha
            partido.setDate(calendar.getTime());
            calendar.add(Calendar.HOUR_OF_DAY, 2);
            if (calendar.get(Calendar.HOUR_OF_DAY) > 20) {
                calendar.add(Calendar.HOUR, 14);
            }

            // Arbitro
            int numero = (int) Math.floor(Math.random() * (arbitroArrayList.size() - 1));
            partido.setArbitro(arbitroArrayList.get(numero));
            arbitroArrayList.remove(numero);

            // Jugar el Partido
            if (Automatico) {
                Generador.jugarPartido(partido);
            }
        }
    }

    // Crea los emparejamientos en funcion de la jornada.
    private static Partido[] crucesEquipos(Equipo[] equipos, int jornada) {
        int numeroEquipos = equipos.length;                 // 10
        int numeroPartidos = (numeroEquipos / 2);           // 5
        int numeroJornada = jornada;                        // n
        int numeroJornadaMax = (numeroEquipos - 1) * 2;         // 18
        int primeraMitadJornada = numeroEquipos - 1;          // 9
        Partido[] partidos = new Partido[numeroPartidos];

        if (jornada >= 0 && jornada < numeroJornadaMax) {

            ArrayList<Equipo> arrayListEquipos = new ArrayList<>(Arrays.asList(equipos).subList(0, (numeroEquipos)));

            // Metodo para poner a descansar
            if (numeroEquipos % 2 != 0) {
                if (jornada < primeraMitadJornada) {
                    arrayListEquipos.remove(numeroJornada);
                } else {
                    int jornadaTemporal = numeroJornada;
                    jornadaTemporal -= (numeroEquipos - 1);
                    arrayListEquipos.remove(jornadaTemporal);
                }
            }

            // La jornada 0 no hace ninguna rotacion
            if (jornada < primeraMitadJornada) {

                for (int j = 0; j < numeroJornada; j++) {
                    int temporal = arrayListEquipos.size() - 1;
                    Equipo equipoTemporal = arrayListEquipos.get(temporal);
                    arrayListEquipos.remove(temporal);
                    arrayListEquipos.add(1, equipoTemporal);
                }
                if (jornada % 2 != 0) {
                    Collections.reverse(arrayListEquipos);
                }
            } else {
                for (int j = 0; j < numeroJornada; j++) {
                    Equipo temporal = arrayListEquipos.get(1);
                    arrayListEquipos.remove(1);
                    arrayListEquipos.add(arrayListEquipos.size() - 1, temporal);  //-2
                }

                if (jornada % 2 != 0) {
                    Collections.reverse(arrayListEquipos);
                }
            }

            int ultimo = arrayListEquipos.size() - 1;

            //Crear partidos con el arraylist
            for (int i = 0; i < numeroPartidos; i++) {
                Equipo equipoVisitante = arrayListEquipos.get(i);
                Equipo equipoLocal = arrayListEquipos.get(ultimo);
                Partido partido = new Partido(equipoLocal, equipoVisitante);
                //partido.setEquipoLocal(equipoVisitante);
                //partido.setEquipoVisitante(equipoLocal);
                partidos[i] = partido;
                //System.out.println("Jornada: "+jornada + " Partido: "+ i +" "+partido.getEquipoLocal().getNombre()+ " -- VS -- " +partido.getEquipoVisitante().getNombre() +" "+ultimo);
                ultimo--;
            }
        }

        return partidos;
    }

    public int getNumeroJornada() {
        return numeroJornada;
    }

    public void setNumeroJornada(int numeroJornada) {
        this.numeroJornada = numeroJornada;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public Partido[] getPartido() {
        return partido;
    }

    public void setPartido(Partido[] partido) {
        this.partido = partido;
    }

    public boolean isTerminada() {
        return terminada;
    }

    public void setTerminada(boolean terminada) {
        this.terminada = terminada;
    }

    public void terminar() {
        this.terminada = true;
    }

    @Override
    public String toString() {
        return "Jornada Numero =" + numeroJornada +
                ", Fecha = " + Fecha +
                ", \n Partidos = " + Arrays.toString(partido);
    }
}
