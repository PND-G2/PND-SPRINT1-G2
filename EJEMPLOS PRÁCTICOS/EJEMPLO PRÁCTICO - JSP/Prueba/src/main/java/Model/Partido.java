package Model;

import java.util.Date;

public class Partido {
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int golEquipoLocal;
    private int golEquipoVisitante;
    private Date date;
    private Arbitro arbitro;
    private Alineacion alineacionLocales;
    private Alineacion alineacionVisitantes;

    // Puede nacer vacío
    public Partido() {
    }

    // Partido Automatico
    public Partido(Equipo equipoLocal, Equipo equipoVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }

    public Alineacion getAlineacionLocales() {
        return alineacionLocales;
    }

    public void setAlineacionLocales(Alineacion alineacionLocales) {
        this.alineacionLocales = alineacionLocales;
    }

    public Alineacion getAlineacionVisitantes() {
        return alineacionVisitantes;
    }

    public void setAlineacionVisitantes(Alineacion alineacionVisitantes) {
        this.alineacionVisitantes = alineacionVisitantes;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getGolEquipoLocal() {
        return golEquipoLocal;
    }

    public void setGolEquipoLocal(int golEquipoLocal) {
        this.golEquipoLocal = golEquipoLocal;
    }

    public int getGolEquipoVisitante() {
        return golEquipoVisitante;
    }

    public void setGolEquipoVisitante(int golEquipoVisitante) {
        this.golEquipoVisitante = golEquipoVisitante;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "\nPartido" + "\nFecha: " + date +
                "\n" + equipoLocal.getNombre() + " " + golEquipoLocal + " - " +
                " " + golEquipoVisitante + " " + equipoVisitante.getNombre() +
                "\n" + arbitro;
    }
}