package Model;

public final class Jugador extends Persona {
    private String categoria;
    private String posicion;
    private int dorsal;
    private Equipo equipo;
    private int partidoJugados;
    private int gol;
    private int golEncajados;
    private int duracionLesion;

    public Jugador() {
    }

    public int getGolEncajados() {
        return golEncajados;
    }

    public void setGolEncajados(int golEncajados) {
        this.golEncajados = this.golEncajados + golEncajados;
    }

    public int getPartidoJugados() {
        return partidoJugados;
    }

    public void setPartidoJugados(int partidoJugados) {
        this.partidoJugados = partidoJugados;
    }

    public void setEdad(int edad) {
        super.setEdad(edad);
        categoria = setCategoria(edad);
    }

    public String getCategoria() {
        return categoria;
    }

    private String setCategoria(int edad) {
        return switch (edad) {
            case 4, 5 -> "Chupetín";
            case 6, 7 -> "Prebenjamín";
            case 8, 9 -> "Benjamín";
            case 10, 11 -> "Alevín";
            case 12, 13 -> "Infantil";
            case 14, 15 -> "Cadete";
            case 16, 17, 18 -> "Juvenil";
            default -> "N/A";
        };
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getGol() {
        return gol;
    }

    public void setGol(int gol) {
        this.gol = this.gol + gol;
    }

    public int getDuracionLesion() {
        return duracionLesion;
    }

    public void setDuracionLesion(int duracionLesion) {
        this.duracionLesion = duracionLesion;
    }

	public void addPartidoJugados() {
		this.partidoJugados++;
	}

	public void reducirLesion() {
		this.duracionLesion--;
	}

	@Override
    public String toString() {
        return super.toString() + " " + categoria +
                "\n Equipo: " + equipo.getNombre() +
                "\n Dorsal: " + dorsal +
                "\n Posición: " + posicion + "\n";
    }


}