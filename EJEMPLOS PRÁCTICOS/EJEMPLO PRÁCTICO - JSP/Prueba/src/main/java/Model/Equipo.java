package Model;

import java.util.Arrays;

public class Equipo {

    private String nombre;
    private String club;
    private Entrenador entrenador;
    private Jugador[] jugadores;
    private String equipacionLocal;
    private String equipacionVisitante;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public String getEquipacionLocal() {
        return equipacionLocal;
    }

    public void setEquipacionLocal(String equipacionLocal) {
        this.equipacionLocal = equipacionLocal;
    }

    public String getEquipacionVisitante() {
        return equipacionVisitante;
    }

    public void setEquipacionVisitante(String equipacionVisitante) {
        this.equipacionVisitante = equipacionVisitante;
    }

    @Override
    public String toString() {
        return "\n Equipo: " + this.nombre +
                "\n Club: " + this.club +
                "\n Jugadores :" + Arrays.toString(this.jugadores);
    }

    public String toStringSinJugadores() {
        return "\n Equipo: " + this.nombre +
                "\n Club: " + this.club +
                this.entrenador.toString();
    }

    public String toStringCompleto() {
        return "\n Equipo: " + this.nombre +
                "\n Club: " + this.club +
                "\n" + this.entrenador.toString() +
                "\n Jugadores :" + Arrays.toString(this.jugadores);
    }

}