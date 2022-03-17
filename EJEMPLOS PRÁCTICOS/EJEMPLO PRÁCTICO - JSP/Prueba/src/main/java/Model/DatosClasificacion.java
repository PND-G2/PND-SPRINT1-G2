package Model;

public class DatosClasificacion {

    private Equipo equipo;
    private int puntosTotales;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesFavor;
    private int golesContra;
    private int diferenciaGoles;

    public DatosClasificacion() {
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(int puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public void addPartidosJugados() {
        this.partidosJugados = this.partidosJugados + 1;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    public int getDiferenciaGoles() {
        return diferenciaGoles;
    }

    public void setDiferenciaGoles(int diferenciaGoles) {
        this.diferenciaGoles = diferenciaGoles;
    }

    public void addGolesFavor(int goles) {
        this.golesFavor += goles;
        this.diferenciaGoles += goles;
    }

    public void addGolesContra(int goles) {
        this.golesContra -= goles;
        this.diferenciaGoles -= goles;
    }

    public void addPartidoGanado() {
        this.partidosJugados++;
        this.partidosGanados++;
        this.puntosTotales += 3;
    }

    public void addPartidoEmpatado() {
        this.partidosJugados++;
        this.partidosEmpatados++;
        this.puntosTotales++;
    }

    public void addPartidoPerdido() {
        this.partidosJugados++;
        this.partidosPerdidos++;
    }

    @Override
    public String toString() {
        String tabulaciones = "";
        if (this.equipo.getNombre().length() >= 11 && this.equipo.getNombre().length() <= 14) {
            tabulaciones = "\t\t\t\t\t\t\t\t\t\t"; // 9
        } else if (this.equipo.getNombre().length() >= 15 && this.equipo.getNombre().length() <= 19) {
            tabulaciones = "\t\t\t\t\t\t\t\t\t"; // 8
        } else if (this.equipo.getNombre().length() >= 20 && this.equipo.getNombre().length() <= 23) {
            tabulaciones = "\t\t\t\t\t\t\t\t"; // 7
        } else if (this.equipo.getNombre().length() >= 24 && this.equipo.getNombre().length() <= 27) {
            tabulaciones = "\t\t\t\t\t\t\t"; // 6
        } else if (this.equipo.getNombre().length() >= 28 && this.equipo.getNombre().length() <= 31) {
            tabulaciones = "\t\t\t\t\t\t"; // 5
        } else if (this.equipo.getNombre().length() >= 32 && this.equipo.getNombre().length() <= 35) {
            tabulaciones = "\t\t\t\t\t"; // 5
        } else if (this.equipo.getNombre().length() >= 36 && this.equipo.getNombre().length() <= 39) {
            tabulaciones = "\t\t\t\t"; // 5
        } else {
            tabulaciones = "\t\t\t";
        }


        return this.equipo.getNombre() + tabulaciones + " " + this.partidosJugados + "\t" + " " + this.partidosGanados + "\t" + " " +
                this.partidosEmpatados + "\t" + " " + this.partidosPerdidos + "\t" + " " + this.golesFavor + "\t" + " " + this.golesContra + "\t" +
                this.diferenciaGoles + "\t" + " " + this.puntosTotales + "\n";
    }

}
