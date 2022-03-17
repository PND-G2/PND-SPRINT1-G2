package Model;

import Controller.Generador;

import java.util.Arrays;

public class Liga {

    private String nombre;
    private Calendario calendario;
    private Clasificacion clasificacion;
    private Equipo[] equipos;
    private Arbitro[] arbitros;

    // Liga Automática con 10 equipos y 0 Argumentos
    public Liga() {
        this.nombre = "C3SHUR H4zt3 PR0";
        this.equipos = Generador.crearEquipos(10, 12, 24);
        this.arbitros = Generador.crearArbitro(equipos.length);
        this.calendario = new Calendario(equipos, arbitros, (equipos.length - 1) * 2);
        this.clasificacion = new Clasificacion(calendario, equipos);
    }

    // Liga Manual con 4 argumentos
    public Liga(String nombre, int numeroEquipos, int edadJugadores, int numeroJugadores, int PARTIDOSJUGADOS) {
        this.nombre = nombre;
        this.equipos = Generador.crearEquipos(numeroEquipos, edadJugadores, numeroJugadores);
        this.arbitros = Generador.crearArbitro(equipos.length);
        this.calendario = new Calendario(equipos, arbitros, PARTIDOSJUGADOS);
        this.clasificacion = new Clasificacion(calendario, equipos);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Equipo[] getEquipos() {
        return equipos;
    }

    public void setEquipos(Equipo[] equipos) {
        this.equipos = equipos;
    }

    public Arbitro[] getArbitros() {
        return arbitros;
    }

    public void setArbitros(Arbitro[] arbitros) {
        this.arbitros = arbitros;
    }

    @Override
    public String toString() {
        return "Liga: " + nombre +
                ", Equipos = " + Arrays.toString(equipos) + "\n" +
                ", Arbitros = " + Arrays.toString(arbitros) + "\n";
    }
}
