package Model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Calendario {

    private Jornada[] jornada;

    // Automático. No hay forma manual
    public Calendario(Equipo[] equipos, Arbitro[] arbitros, int partidosJugados) {

        this.jornada = new Jornada[(equipos.length - 1) * 2];

        Calendar calendar = new GregorianCalendar(2022, Calendar.JANUARY, 1);

        // for (int i = 0; i < ((equipos.length-1)*2); i++) {
        for (int i = 0; i < partidosJugados; i++) {
            int numeroJornada = (i + 1);

            calendar.set(Calendar.HOUR_OF_DAY, 12);

            this.jornada[i] = new Jornada(equipos, arbitros, i, calendar, true);
            this.jornada[i].setNumeroJornada(numeroJornada);
            this.jornada[i].setFecha(calendar.getTime());
            this.jornada[i].terminar();
            calendar.add(Calendar.DAY_OF_WEEK, 6);

        }
    }

    public Jornada[] getJornada() {
        return jornada;
    }

    public void setJornada(Jornada[] jornada) {
        this.jornada = jornada;
    }

    @Override
    public String toString() {
        return "Calendario{" +
                "jornada=" + Arrays.toString(jornada) +
                '}';
    }
}
